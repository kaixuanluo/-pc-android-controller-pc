package socketKx;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JPanel;

import listeners.PhoneAllListeners;
import persenter.PhoneItemPresenter;

public class PhoneAll implements PhoneAllListeners {

	protected List<PhoneItem> phoneItemList = new ArrayList<PhoneItem>();

	protected JPanel mJpPhoneList;

	public PhoneAll() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void powerOffAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public void reBootAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public void backAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public void homeAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public void installAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public void startMonitorAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public void stopMonitorAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public void monitorScreenOffAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public void monitorScreenOnAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initServerSocketAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public JPanel initPhoneListAll(JPanel jpPhoneList, PhoneItemPresenter pip) {
		// TODO Auto-generated method stub

		return this.mJpPhoneList = jpPhoneList;

	}

	@Override
	public void addPhone(String phoneIp, PhoneItem phoneItemNew) {
		// TODO Auto-generated method stub

		JPanel newItem = phoneItemNew.getmJpItem();
		String newIp = phoneItemNew.getmIp();

		System.out.println("手机 " + phoneIp + " 上线了 ...");
		int size = phoneItemList.size();
		if (size == 0) {
			mJpPhoneList.add(newItem);
			phoneItemList.add(phoneItemNew);
		} else if (size == 1) {
			int result = newIp.compareTo(phoneItemList.get(0).getmIp());
			if (result > 0) {
				mJpPhoneList.add(newItem);
				phoneItemList.add(phoneItemNew);
			} else if (result == 0) {
				mJpPhoneList.remove(0);
				phoneItemList.remove(0);
				mJpPhoneList.add(newItem);
				phoneItemList.add(phoneItemNew);
			} else if (result < 0) {
				mJpPhoneList.add(newItem, 0);
				phoneItemList.add(0, phoneItemNew);
			}
		} else {
			for (int i = 0; i < size; i++) {

				// if (i == 0) {
				// mJpPhoneList.add(newItem);
				// phoneItemList.add(phoneItemNew);
				// break;
				// } else

				PhoneItem phoneItem1 = phoneItemList.get(i);
				String getmIpI = phoneItem1.getmIp();

				if (i == size - 1) {
					int result = newIp.compareTo(getmIpI);
					// mJpPhoneList.add(newItem);
					// phoneItemList.add(phoneItemNew);

					if (result > 0) {
						mJpPhoneList.add(newItem);
						phoneItemList.add(phoneItemNew);
					} else if (result == 0) {
						mJpPhoneList.remove(i);
						phoneItemList.remove(i);
						mJpPhoneList.add(newItem);
						phoneItemList.add(phoneItemNew);
					} else if (result < 0) {
						mJpPhoneList.add(newItem, i);
						phoneItemList.add(i, phoneItemNew);
					}

					break;
				} else {

					int j = i + 1;
					PhoneItem phoneItem2 = phoneItemList.get(j);
					String getmIpJ = phoneItem2.getmIp();

					if (newIp.compareTo(getmIpI) > 0 && newIp.compareTo(getmIpJ) < 0) {
						mJpPhoneList.add(newItem, j);
						phoneItemList.add(j, phoneItemNew);
						break;
					} else if (newIp.compareTo(getmIpI) == 0) {
						mJpPhoneList.remove(phoneItem1.getmJpItem());
						phoneItemList.remove(phoneItem1);
						mJpPhoneList.add(newItem, i);
						phoneItemList.add(i, phoneItemNew);
						break;
					} else {
					}
				}
			}
		}
		mJpPhoneList.updateUI();
		phoneItemNew.mediaPlay();
	}

	// 举例来说吧
	// String ipAddr1 = 192.168.0.11;
	// String ipAddr2 = 192.168.0.22;
	// int result = ipAddr1.compareTo(ipAddr2);
	// 结果是result < 0 ，说明ipAddr1 < ipAddr2 。
	// 注:compareTo的作用如下
	// 如果参数字符串等于此字符串，则返回 0 值;如果按字典顺序此字符串小于字符串参数，则返回一个小于 0
	// 的值;如果按字典顺序此字符串大于字符串参数，则返回一个大于 0 的值。

	@Override
	public void removePhone(String phoneIp, PhoneItem phoneItem) {
		// TODO Auto-generated method stub

		phoneItem.mediaStop();

		System.out.println("手机  " + phoneIp + " 下线了...");
		if (mJpPhoneList == null) {
			System.err.println("mJpPhoneList is null");
			return;
		}
		Component[] components = mJpPhoneList.getComponents();
		if (components.length <= 0) {
			System.err.println("components is null");
			return;
		}
		List<Component> asList = Arrays.asList(components);
		if (asList.contains(phoneItem.getmJpItem())) {
			mJpPhoneList.remove(phoneItem.getmJpItem());
			mJpPhoneList.updateUI();
		} else {
			System.out.println(phoneItem.getmJpItem().toString());
			for (int i = 0; i < asList.size(); i++) {
				System.out.println(asList.get(i).toString());
			}
			System.err.println("当前不包含需要删除的组件...");
		}
	}

}
