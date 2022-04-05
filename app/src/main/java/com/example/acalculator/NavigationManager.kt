package com.example.acalculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import java.util.*
import kotlin.collections.ArrayList

object NavigationManager {

    private fun placeFragment(fm: FragmentManager, fragment: Fragment) {
        val transition = fm.beginTransaction()
        transition.replace(R.id.frame, fragment)
        transition.addToBackStack(null)
        transition.commit()
    }

    fun goToCalculatorFragment(fm: FragmentManager, ops: ArrayList<OperationUi>) {
        placeFragment(fm,CalculatorFragment.newInstance(ops))
    }

    fun goToHistoryFragment(fm: FragmentManager, ops: ArrayList<OperationUi>) {
        placeFragment(fm, HistoryFragment.newInstance(ops))
    }

    fun goToDetailsFragment(fm: FragmentManager, ops: OperationUi) {
        placeFragment(fm, OperationDetailFragment.newInstance(ops))
    }

}