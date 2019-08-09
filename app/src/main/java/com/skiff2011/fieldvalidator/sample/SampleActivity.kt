package com.skiff2011.fieldvalidator.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import kotlinx.android.synthetic.main.activity_sample.pager
import kotlinx.android.synthetic.main.activity_sample.tabLayout

class SampleActivity : AppCompatActivity() {


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_sample)
    pager.adapter = SamplePagerAdapter(supportFragmentManager)
    tabLayout.setupWithViewPager(pager)
  }
}

class SamplePagerAdapter(fm: FragmentManager) :
  FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
  override fun getItem(position: Int): Fragment = when (position) {
    0 -> FieldValidatorFragment.getInstance()
    1 -> FieldAutoValidatorFragment.getInstance()
    else -> throw IllegalArgumentException("No Fragment for this index")
  }

  override fun getPageTitle(position: Int): CharSequence? = when (position) {
    0 -> "FieldValidator"
    1 -> "FieldAutoValidator"
    else -> null
  }

  override fun getCount(): Int = 2

}
