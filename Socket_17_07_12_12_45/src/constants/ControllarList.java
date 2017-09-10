package constants;

import java.util.ArrayList;
import java.util.List;

public class ControllarList {

	private static List mControllList;

	public static List<String> getControllarList() {

		if (mControllList == null || mControllList.isEmpty()) {
			mControllList = new ArrayList();
			mControllList.add(ConstantsStr.REBOT_ALL);
			mControllList.add(ConstantsStr.POWER_OFF_ALL);
			mControllList.add(ConstantsStr.BACK_ALL);
			mControllList.add(ConstantsStr.HOME_ALL);
			mControllList.add(ConstantsStr.MONITOR_START);
			mControllList.add(ConstantsStr.MONITOR_STOP);
			mControllList.add(ConstantsStr.MONITOR_SCREEN_ON);
			mControllList.add(ConstantsStr.MONITOR_SCREEN_OFF);
			mControllList.add(ConstantsStr.CHANGE_PHONE_INFO);
			mControllList.add(ConstantsStr.CHANGE_PHONE_LOCALTION);
			mControllList.add(ConstantsStr.WECHAT_AUTO_SHAKE);
			mControllList.add(ConstantsStr.WECHAT_AUTO_HI);
			mControllList.add(ConstantsStr.WECHAT_AUTO_REPLY);
			mControllList.add(ConstantsStr.WECHAT_GROUP);
			mControllList.add(ConstantsStr.MOMO_AUTO_HI);
			mControllList.add(ConstantsStr.MOMO_AUTO_REPLY);
			mControllList.add(ConstantsStr.MOMO_GROUP);
		}

		return mControllList;
	}

}
