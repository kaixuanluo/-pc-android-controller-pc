package baseView;

import javax.swing.JFrame;

import persenter.PhoneAllPresenter;
import persenter.PhoneItemPresenter;

public abstract class BaseControllar<P1 extends PhoneAllPresenter, 
P2 extends PhoneItemPresenter> extends BaseJFrame{

	private P1 mAllPresenter;
	private P2 mItemPresenter;
	
	public BaseControllar() {
		// TODO Auto-generated constructor stub
	}
	
	public abstract P1 initAllPresenter();
	public abstract P2 initItemPresenter();
	
	public P1 getAllPresenter () {
		if (mAllPresenter == null) {
			mAllPresenter = initAllPresenter();
			return mAllPresenter;
		} else {
			return mAllPresenter;
		}
		
	}
	public P2 getItemPresenter () {
		if (mItemPresenter == null) {
			mItemPresenter = initItemPresenter();
			return mItemPresenter;
		} else {
			return mItemPresenter;
		}
		
	}
	

}
