package nupt.vivi;
public class MenuViewGoThread extends Thread{
	boolean flag = true;//ѭ�����λ
	int sleepSpan = 300;//˯�ߵĺ�����
	PushBoxActivity pushBoxActivity;
	public MenuViewGoThread(PushBoxActivity pushBoxActivity){
		this.pushBoxActivity = pushBoxActivity;
	}
	@Override
	public void run(){
		while(flag){
			if(pushBoxActivity.menuView != null){
				if(pushBoxActivity.menuView.menubackgroudX < -320){//���ƶ���Զʱ���������õ�0
					pushBoxActivity.menuView.menubackgroudX = 0;
				}
				pushBoxActivity.menuView.menubackgroudX -= 2;//ÿ��ѭ��������������λ
				try{
					Thread.sleep(sleepSpan);//˯��
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}	
}