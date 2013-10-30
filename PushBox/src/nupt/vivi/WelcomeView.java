package nupt.vivi;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;

public class WelcomeView extends SurfaceView implements SurfaceHolder.Callback, OnClickListener{
	PushBoxActivity pushBoxActivity;
	WelcomeViewDrawThread welcomeViewDrawThread = null;
	Bitmap bitmap;
	Bitmap wallLeft;//�����ǽ
	Bitmap wallRight;//�����ǽ
	Bitmap iron;//����
	Bitmap woodLeft;//�����ľ��
	Bitmap woodRight;//�����ľ��
	Bitmap background;
	Bitmap mountain;//������ɽ
	
	int wallLeftX = 15;//ǽ������
	int wallLeftY = 10;
	int wallRightX = 150;
	int wallRightY = 10;

	int ironX = 15;//���ŵ�����
	int ironY = 10;
	
	int woodLeftX = 15;//ľ�ŵ�����
	int woodLeftY = 10;
	int woodRightX = 150;
	int woodRightY = 10; 		
	public WelcomeView(PushBoxActivity pushBoxActivity) {//������
		super(pushBoxActivity);
		this.pushBoxActivity = pushBoxActivity;
		getHolder().addCallback(this);
		welcomeViewDrawThread = new WelcomeViewDrawThread(this,getHolder());
		wallRight = BitmapFactory.decodeResource(getResources(), R.drawable.wallright);
		wallLeft = BitmapFactory.decodeResource(getResources(), R.drawable.wallleft); 
		bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.image4);
		iron = BitmapFactory.decodeResource(getResources(), R.drawable.image2);
		woodLeft = BitmapFactory.decodeResource(getResources(), R.drawable.image33);
		woodRight = BitmapFactory.decodeResource(getResources(), R.drawable.image3);
		background = BitmapFactory.decodeResource(getResources(), R.drawable.background);//������ˮ
		mountain = BitmapFactory.decodeResource(getResources(), R.drawable.mountain2);		
	}
	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(Color.WHITE);//������ɫ
		canvas.drawBitmap(background, 0, 0, new Paint());//���Ʊ���
		canvas.drawBitmap(mountain, 0, 0, new Paint());//�����ɽͼƬ
		canvas.drawBitmap(wallLeft, wallLeftX, wallLeftY,new Paint());//ǽ������
		canvas.drawBitmap(wallRight, wallRightX, wallRightY,new Paint());//ǽ������
		canvas.drawBitmap(iron, ironX, ironY,new Paint());//����
		canvas.drawBitmap(woodLeft, woodLeftX, woodLeftY,new Paint());//ľͷ������
		canvas.drawBitmap(woodRight, woodRightX, woodRightY,new Paint());//ľͷ������
		canvas.drawBitmap(bitmap, 0, 0, new Paint());
		this.setOnClickListener(this);
	}	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
	}
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		welcomeViewDrawThread.setFlag(true);
		welcomeViewDrawThread.start();//����ˢ֡�߳�
	}
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        welcomeViewDrawThread.setFlag(false);//ֹͣˢ֡�߳�
        while (retry) {
            try {
            	welcomeViewDrawThread.join();//�ȴ�ˢ֡�߳̽���
                retry = false;
            } 
            catch (InterruptedException e) {//���ϵ�ѭ����ֱ���ȴ����߳̽���
            }
        }
	}
	@Override
	public void onClick(View v) {
		pushBoxActivity.myHandler.sendEmptyMessage(1);
	}	
}