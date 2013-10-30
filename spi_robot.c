/*version:2.0*/
#include<stdlib.h>
#include<sys/types.h>
#include<string.h>
#include<sys/wait.h>
#include<sys/socket.h>
#include<netinet/in.h>
#include<net/if.h>
#include<net/if_arp.h>
#include<arpa/inet.h>
#include<stdio.h>
#include<unistd.h>
#include<pthread.h>
#include<netdb.h>
#include <fcntl.h>
#include <sys/ioctl.h>
#include <sys/stat.h>
#include <linux/types.h>
#include <linux/spi/spidev.h>
#include <signal.h>

#define PORT  6666
static char cmd[100];
static int sock_fd;

//FFmpeg variables are defined here ////////////////////////////////
static pid_t pc,pid_ffmpeg,pid_ccs;
char * ffmpeg_av[]={"ffmpeg","-f","video4linux","-s","cif","-r","08",
            "-b","0060000","-g","20","-i","/dev/video0","-vcodec","mpeg4","-f",
           "rtp","rtp:192.168.006.008:12346","-f","oss","-ar",
           "16000","-ab","32000","-ac","1","-i","/dev/dsp","-acodec",
           "mp2","-f","rtp","rtp:192.168.006.008:12386",NULL};
char * ffmpeg_audio[]={"ffmpeg","-f","oss","-ar",
           	"16000","-ab","32000","-ac","1","-i","/dev/dsp","-acodec",
	        "mp2","-f","rtp","rtp:192.168.006.008:12386",NULL};
char * ffmpeg_video[]={"ffmpeg ","-f","video4linux","-s","cif","-r","08",
	        "-b","0060000","-g","20","-i","/dev/video0","-vcodec","mpeg4","-f",
	        "rtp","rtp:192.168.006.008:12346",NULL};
char *const *pFFMPEG = ffmpeg_av; 

#define LOCAL_IP  "192.168.6.8"
#define MAX_NET_TEST  60         //MAX time of network(ppp0) test
//END of FFMPEG declare

/*spi device open (add by zb)*/
static int spi_fd;

/*robot's command*/
static unsigned char buff[] = {0xE1, 0xE2, 0xE3, 0xE4, 0xE5, 0xEE,0xFF,0xE7,0xE9};


/*convert a cmdstring to a [argc argv],argc is the num of paramter
 return :argc :number of parameters,NULL are not included,but NULL
 are included at the end of each **argv,and the last *argv    */
int
cmd2argcv(const char *cmdstr,char ***argv){
	ssize_t argc = 0;
	const char *snew;
	char *pchar;
	ssize_t  i;

#define delimiter " \t"  /*parameters are isolated by deliiter ' '*/

	if((cmdstr == NULL) || (argv == NULL))
			return -1;

	*argv = NULL;
	/*snew is real start of string*/
	snew = cmdstr + strspn(cmdstr,delimiter);
	if((pchar  = malloc(strlen(snew) + 1)) == NULL)
			return -1;
	strcpy(pchar,snew);

	argc = 0;
	if(strtok(pchar,delimiter) != NULL)
		for(argc = 1; strtok(NULL,delimiter) !=NULL; argc++);

	/*creat argument array for ptrs to tokens*/
	if((*argv = malloc((argc + 1)*sizeof(char *))) ==NULL){
		free(pchar);
		return -1;
	}
	if(argc == 0)
		free(pchar);
	else{
		strcpy(pchar,snew);
		**argv = strtok(pchar,delimiter);
		for(i = 1; i < argc; i++)
			*(*argv + i) =strtok(NULL,delimiter);
	}
	*(*argv + argc) = NULL;
	return argc;
}

/*func_name:get_if_stat
 * input_arg:if_name,the net interface you want to get its running status,such as:eth0,ppp0,etc
 * return:-1---error,such as no such interface,etc.
 * 	   1---the interface is up
 * 	   0---the  interface is down
 */

int
get_if_stat(const char *if_name){
	int   sock,flags;
	 int len_sockaddr = sizeof(struct sockaddr);
	 struct   ifreq *ifr;
	 ifr = malloc(len_sockaddr + IF_NAMESIZE);  /*sufcient large*/
	 /*TCP or UDP */
	 if((sock  =  socket(AF_INET,SOCK_DGRAM,0)) < 0){
			 perror( "creat socket error");
			 return  -1;
	 }

	 strncpy(ifr->ifr_name,if_name,IF_NAMESIZE);
	 //ifr->ifr_name[IF_NAMESIZE-1]  =  '\0';   /*as a string */

	 if(ioctl(sock, SIOCGIFADDR, ifr) < 0) {
		 	 perror("get if-status error");
		 	 return -1;
	 	 }
	 flags = ifr->ifr_flags;
	 free(ifr);
	 return(flags & IFF_UP);
}

/*************************************************************************************
func_name:execCMD
function:do  related task according to received command
*************************************************************************************/
int execCMD(char * cmd){
	char * pcCMD;    //for ffmpeg parmeters
   char sendbuf[50]={0};
   int i;
  if(strstr(cmd,"go_forward")!=NULL){
			 for(i=0;i<5;i++){
					 usleep(3000);
					 write(spi_fd, &buff[7], 1);
					 usleep(3000);
					 write(spi_fd, &buff, 1);
					 usleep(3000);
					 write(spi_fd, &buff[8], 1);
			 }
   sprintf(sendbuf,"begin to go forward");
  }
	else if(strstr(cmd,"back_off")!=NULL){
				for(i=0;i<5;i++){
						usleep(3000);
						write(spi_fd, &buff[7], 1);
						usleep(3000);
						write(spi_fd, &buff[5], 1);
						usleep(3000);
						write(spi_fd, &buff[8], 1);
				}
   sprintf(sendbuf,"begin to back off");
	}
	else if(strstr(cmd,"turn_left")!=NULL){
				for(i=0;i<5;i++){
						usleep(3000);
						write(spi_fd, &buff[7], 1);
						usleep(3000);
						write(spi_fd, &buff[3], 1);
						usleep(3000);
						write(spi_fd, &buff[8], 1);
					}
				sprintf(sendbuf,"begin to turn_left");
	}
	else if(strstr(cmd,"turn_right")!=NULL){
				for(i=0;i<5;i++){
						usleep(3000);
						write(spi_fd, &buff[7], 1);
						usleep(3000);
						write(spi_fd, &buff[4], 1);
						usleep(3000);
						write(spi_fd, &buff[8], 1);
					}
				sprintf(sendbuf,"begin to turn_right");
	}
	else if(strstr(cmd,"speedup")!=NULL){
				usleep(3000);
				write(spi_fd, &buff[7], 1);
				usleep(3000);
				write(spi_fd, &buff[1], 1);
				usleep(3000);
				write(spi_fd, &buff[8], 1);

      sprintf(sendbuf,"speed up!!!");
	}
	else if(strstr(cmd,"slowdown")!=NULL){
				usleep(3000);
				write(spi_fd, &buff[7], 1);
				usleep(3000);
				write(spi_fd, &buff[2], 1);
				usleep(3000);
				write(spi_fd, &buff[8], 1);

      sprintf(sendbuf,"slow down!!!");
	}
	else if(strstr(cmd,"stop_moving")!=NULL)
	{
		for(i=0;i<5;i++){
				usleep(3000);
				write(spi_fd, &buff[7], 1);
				usleep(3000);
				write(spi_fd, &buff[6], 1);
				usleep(3000);
				write(spi_fd, &buff[8], 1);
			}
   sprintf(sendbuf,"stop moving");
	}
  /*adjut ffmpeg paramters
   *the ffmpeg paramters formats must be as followed:
  *ffmpeg [-video] [-r ***] [-b ***]
  *ffmpeg [-audio]  [-ar **] [-ab **]
  *ffmpeg [-av]  [-r ***] [-b ***] [-ar **] [-ab **]
  */
	else if((pcCMD = strstr(cmd,"ffmpeg"))!=NULL){
			int num_argc;
			char **ffmpeg_argv;
			num_argc = cmd2argcv(pcCMD,&ffmpeg_argv);

			if(!strcmp(ffmpeg_argv[1],"-av")  && (num_argc == 10)){
					pFFMPEG = ffmpeg_av;
					strncpy(ffmpeg_av[6],ffmpeg_argv[3],3); //video_frame_rate
					strncpy(ffmpeg_av[8],ffmpeg_argv[5],8); //video_bit_rate
					strncpy(ffmpeg_av[21],ffmpeg_argv[7],6); //audio_sample_rate
					strncpy(ffmpeg_av[23],ffmpeg_argv[9],6); //audio_bit_rate
			}
			else if (!strcmp(ffmpeg_argv[1],"-video")  && (num_argc == 6)){
					pFFMPEG = ffmpeg_video;
					strncpy(ffmpeg_video[6],ffmpeg_argv[3],3); //video_frame_rate
					strncpy(ffmpeg_video[8],ffmpeg_argv[5],8); //video_bit_rate
			}
			else if(!strcmp(ffmpeg_argv[1],"-audio")  && (num_argc == 6)){
					pFFMPEG = ffmpeg_audio;
					strncpy(ffmpeg_audio[4],ffmpeg_argv[3],6); //audio_sample_rate
					strncpy(ffmpeg_audio[6],ffmpeg_argv[5],6); //audio_bit_rate
			}
			else{
					printf("ffmpeg parameters error!Do nothing\n");
					return 1;
			}
		//now,kill the child ffmpeg;and fork another child ffmeg process
		 if(kill(pid_ffmpeg,SIGTERM) == 0)
				  printf("kill child %d success:\n",pid_ffmpeg);
		 else 
					perror("kill");
		//restart the child process again!!!,but,at first ,you should wait the child process exit fully
			if(waitpid(0, NULL,0))
				printf("child process toally exit\n");
			//	printf("wait until the child process exit fully!:%d",wait(NULL));

				//print curent ffmpeg parameter ,just for debug purpose
		/*		char *const *ppFFMPEG = pFFMPEG;
				while(*ppFFMPEG != NULL){
				  printf("%s\t",*ppFFMPEG);
				  ppFFMPEG++;
				}
				fflush(stdout);
		*/
				pc = vfork();
				if(pc < 0)
						perror("Error Refork:");
				//child process-----------------
				else if (pc == 0){
						if (execv("/usr/bin/ffmpeg",pFFMPEG) < 0)    //restart another
								perror("execv FFMPG error!");
						exit(0);
				 }
				else {
						pid_ffmpeg = pc;    //save the child pid for the next kill
						sprintf(sendbuf,"Change para success");
				}
		}
  //unexpected command!!
	else{
		 	 return -1;
	 	 }

	send(sock_fd,sendbuf,strlen(sendbuf)+1,0);  //giveback  related execresult
  //	free(ffmpeg_argv);
	return 0;  //return success!!!
}


/*************************************************************************************
func_ame: revcCMD
input_arg:tmpservaddr--the socket where the data come from
input_arg:cmd-local buffer to store the recv data
function:   receive command form socket,and  print it
*************************************************************************************/
int revcCMD(int tmpservaddr,char * cmd){
	char recvBuf[100]={0}; 
  if(recv(tmpservaddr,recvBuf,100,0) <= 0){
	  	  return 1;
  	  }
	strcpy(cmd,recvBuf);
	printf("%s\n",cmd);
	return 0;
};
/*************************************************************************************
func_name: sig_chld_handler
input:
 //   signal(SIGCHLD,sig_chld_handler);                                 
*************************************************************************************/
/*static void sig_chld_handler(int  SIGNO){
	if(waitpid(0, NULL,WNOHANG))
	printf("child process toally exit\n");
}
*/
int
main(int argc,char **argv){
		struct sockaddr_in servaddr;
		struct hostent *host;

		FILE *fp;
		char ipstr[20] = LOCAL_IP;
		char *p;

		//open spi device/
		spi_fd = open("/dev/spidev0.2", O_RDWR);
		if (spi_fd < 0) {
			  perror("open");
		  return 1;
		}
		/*if you execv this programme like this:a.out -l ,it means that you want to start
		 * a local transfer.In a local transfer ,you need not get the serv_ip from txt message
		 * (actually the serv_ip is included in default ffmpeg parameters(19.168.6.8)).
		 * otherwise,it means that you get serv_ip from txt message
		 */
		if((argc >=2) && (!strcmp(argv[1],"-l"))){
			 pFFMPEG = ffmpeg_av;
			 goto main_work;
		}

//start a child process to read serv_ip from 	txt message,and save it in a temp file--------
	  pc = vfork();
	  if(pc < 0){
	  	perror("Error Refork:");
			}
	  else if (pc == 0){  //in child first
			  if (execl("/bin/ccs","ccs",(char *)0) < 0)
			  perror("execv ccs error!");
			  exit(0);
	  	  }
	  else {
		   pid_ccs = pc;    //save the child pid
				printf("waiting txt command\n");
	   	   	   }
	  if(waitpid(pid_ccs, NULL,0)>0)
		  	  printf("child process toally exit\n");
		else{
					printf("unexpected result\n");
			}
//end of get serv_ip from txt message-------------------------------------

	/*read the tmp file to get serv_ip------------------------------------*/
		if((fp=fopen("/usr/nuf","r"))==NULL){
				perror("open file error");
				return -1;
			}
	 p = ipstr;
	 *p = fgetc(fp);
  //get the server ip/
	while(*p!= EOF && *p != 10){
				++p;
				*p=fgetc(fp);
		}
	*p='\0';
  fclose(fp);
	/*Remember to close your open files when you do not use it any more*/

	//Begin to FFMPEG and transfer avdieo at first !!//////////////////////
	   pFFMPEG = ffmpeg_av; 
	  // char rtp[] = "rtp:";
 	   char port_a[] = ":12386";
	   char port_v[] = ":12346";
	   char rtp_port_a[30] = "rtp:" ;  //[rtp:xxx.xxx.xxx.xxx:port]
	   char rtp_port_v[30] = "rtp:" ;  //[rtp:xxx.xxx.xxx.xxx:port]
     //ip should be modified at first time
     ffmpeg_av[17] = strcat(strcat(rtp_port_v,ipstr),port_v);
	  // strcpy(rtp_port,rtp);      //override rtp_port
     ffmpeg_av[32] = strcat(strcat(rtp_port_a,ipstr),port_a);
	   //strcpy(rtp_port,rtp);
	   ffmpeg_audio[15] = rtp_port_a;   //strcat(strcat(rtp_port_a,ipstr),port_a);
	   //strcpy(rtp_port,rtp);
	   ffmpeg_video[17] = rtp_port_v;   //strcat(strcat(rtp_port_v,ipstr),port_v);
	   //printf("ffmpeg_av:%s",ffmpeg_av[30]);

	   int num_net_test = 0;
	   while(get_if_stat("ppp0") < 0){
		   sleep(1);
		    //after ry tMAX_NET_TEST times,the net interface is still down,we give up,and give a report
		   if (num_net_test++ > MAX_NET_TEST){
						   printf("try to get PPP0  not success and exit now!\n");
						   return 1;
		   		}
	   	  }


/*child process for ffmpeg,and father process for socket connection----------------------------*/
main_work:
		pc = vfork();        
		if(pc < 0)
			perror("Error Fork:");
		//child process-----------------
		else if (pc == 0){
			if (execv("/usr/bin/ffmpeg",pFFMPEG) < 0)    //start ffmpeg with default parameters
					perror("execv FFMPG error!");
			exit(0);
		 }
		//END of child process ---------
		else{
    ///father process---------------------------
	     pid_ffmpeg = pc;  //save the child process id ,just for kill/wait the child
   while(1){
      if((host=gethostbyname(ipstr))==NULL){
           fprintf(stderr,"Gethostname error\n");
           exit(-1);
      	  	  	  }

      if( (sock_fd = socket( AF_INET,SOCK_STREAM,0))<0){
           printf("create socket error\n");
           exit(-1);
      	  	  	  }
      else printf("create socket success\n");
       
      servaddr.sin_family = AF_INET;
      servaddr.sin_port = htons(PORT);
      servaddr.sin_addr = *((struct in_addr *)host->h_addr);
      
      while(1){
       /*TCP three times handshakes-*/
    	  	  if(connect(sock_fd,(struct sockaddr *) &servaddr,sizeof(struct sockaddr))<0) {
						    //server is down or time out /;
								 sleep(1);      //wait for one minute to avoid asking for connection too frequently
						    continue;
    	  	  	}
					  else{
								printf("connect success\n");
								break;
						}
     }  //end of three handshakes
		  
		  char sendBuf[]="quit robot's control";

			while(1){
			  if(revcCMD(sock_fd,cmd) != 0){
				  	  printf("recv command error or server is down!\n");
					  close(sock_fd);
				  	  break;
			  	  }

		   if(strstr(cmd,"quit")!=NULL){
						 //sprintf(sendBuf,"quit robot's control");
						 send(sock_fd,sendBuf,strlen(sendBuf)+1,0);
						 close(sock_fd);
						 break;
		   	   	  }
			  			 	
			 if(execCMD(cmd) != 0) {
					   printf("unknow command\n");
			 	 }
		  }   //end of loop for(recv--exec)
   }  //end of loop for (creat socket--connect--recv--exec)
		close(spi_fd);
		return 0;
	 ///END of father process---------------------------
	}
}




