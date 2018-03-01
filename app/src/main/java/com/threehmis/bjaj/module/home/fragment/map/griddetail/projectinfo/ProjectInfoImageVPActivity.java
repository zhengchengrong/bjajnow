/*******************************************************************************
 * Copyright 2011, 2012 Chris Banes.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.threehmis.bjaj.module.home.fragment.map.griddetail.projectinfo;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

import com.threehmis.bjaj.R;
import com.threehmis.bjaj.api.Const;
import com.threehmis.bjaj.utils.GlideTools;
import com.threehmis.bjaj.widget.HackyViewPager;
import com.threehmis.bjaj.widget.photoview.PhotoView;

public class ProjectInfoImageVPActivity extends AppCompatActivity {


	private String imageurl;
	private static  String[] urls ;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_info_image_vp);
		HackyViewPager viewPager = (HackyViewPager) findViewById(R.id.view_pager);
		imageurl = getIntent().getStringExtra(Const.IMAGEURL);
		urls = imageurl.split("#");
		viewPager.setAdapter(new SamplePagerAdapter());
	}

	static class SamplePagerAdapter extends PagerAdapter {


		@Override
		public int getCount() {
			return urls.length;
		}

		@Override
		public View instantiateItem(ViewGroup container, int position) {
			PhotoView photoView = new PhotoView(container.getContext());
			GlideTools.GlideNoId(urls[position],photoView);
			// Now just add PhotoView to ViewPager and return it
			container.addView(photoView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

			return photoView;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}

	}
}
