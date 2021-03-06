package net.cxd.andimclient.view.adapter;

import java.util.ArrayList;
import java.util.List;

import net.cxd.andimclient.R;
import net.cxd.andimclient.app.MyApplication;
import net.cxd.andimclient.util.TimeConventUtil;
import net.cxd.im.entity.UserMsg;
import android.app.Activity;
import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.nb82.view.bitmap.CBitmap;

public class IndexMsgAdapter extends BaseAdapter  {
	private List<UserMsg> list = new ArrayList<UserMsg>();
	private Context context;
	private CBitmap cbitmap;

	public IndexMsgAdapter(Context context) {
		this.context = context;
		MyApplication app = (MyApplication)context;
		cbitmap = (CBitmap) app.cache.get("cbitmap");
	}


	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHoder hoder;
		UserMsg msg = list.get(position);
		if (convertView != null && convertView.getTag() != null) {
			hoder = (ViewHoder) convertView.getTag();
		} else {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.im_msg_item, null);
			hoder = new ViewHoder();
			hoder.msg_head = (ImageView) convertView
					.findViewById(R.id.msg_head);
			hoder.msg_name = (TextView) convertView.findViewById(R.id.msg_name);
			hoder.msg_content = (TextView) convertView
					.findViewById(R.id.msg_content);
			hoder.msg_time = (TextView) convertView.findViewById(R.id.msg_time);
			convertView.setTag(hoder);
		}
		if (msg.getPhotoFile() != null) {
			cbitmap.display(hoder.msg_head, msg.getPhotoFile());
		}
		if ( msg.getNickName()!= null) {
			hoder.msg_name.setText(msg.getNickName());
		}
		if (msg.getContent() != null) {
			hoder.msg_content.setText(msg.getContent());
		}
		if (msg.getTime() != null) {
			hoder.msg_time.setText(TimeConventUtil.timeConvent(msg.getTime()));
		}
		return convertView;
	}

	public List<UserMsg> getList() {
		return list;
	}

	public void setList(List<UserMsg> list) {
		this.list = list;
	}

	static class ViewHoder {
		public ImageView msg_head;
		public TextView msg_name;
		public TextView msg_content;
		public TextView msg_time;
	}

}
