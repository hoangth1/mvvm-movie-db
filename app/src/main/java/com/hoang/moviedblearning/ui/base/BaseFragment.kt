package com.hoang.moviedblearning.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.hoang.moviedblearning.BR
import com.hoang.moviedblearning.R
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment<ViewBinding : ViewDataBinding, ViewModel : BaseViewModel>(@LayoutRes val layoutRes: Int) :
    DaggerFragment(layoutRes) {

    protected lateinit var binding: ViewBinding

    abstract val viewModel: ViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        initView()
        addEvent()

        return binding.root
    }

    abstract fun addEvent()

    abstract fun initView()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            root.isClickable = true
            root.isFocusable = true
            setVariable(BR.viewModel, this@BaseFragment.viewModel)
            lifecycleOwner = viewLifecycleOwner
            executePendingBindings()
        }
        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    onBackPress()
                }
            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.onActivityDestroyed()
    }

    protected fun addFragment(
        fragment: Fragment,
        tag: String? = null,
        direction: TransitionDirection = TransitionDirection.BOTTOM,
        isAddBackStack: Boolean = true
    ) {
        activity?.supportFragmentManager?.beginTransaction()?.apply {
            setAnimationDirection(direction)
            add(R.id.container_main_activity, fragment, tag)
            if (isAddBackStack) addToBackStack(null)
            commit()
        }
    }

    protected fun replaceFragment(
        fragment: Fragment,
        tag: String? = null,
        direction: TransitionDirection = TransitionDirection.BOTTOM,
        isAddBackStack: Boolean = true
    ) {
        activity?.supportFragmentManager?.beginTransaction()?.apply {
            setAnimationDirection(direction)
            replace(R.id.container_main_activity, fragment, tag)
            if (isAddBackStack) addToBackStack(null)
            commit()
        }
    }

    private fun FragmentTransaction.setAnimationDirection(direction: TransitionDirection) {
        when (direction) {
            TransitionDirection.RIGHT -> {
                setCustomAnimations(
                    R.anim.right_in,
                    R.anim.left_out,
                    R.anim.left_in,
                    R.anim.right_out
                )
            }
            TransitionDirection.BOTTOM -> {
                setCustomAnimations(
                    R.anim.bottom_in,
                    R.anim.fade_out,
                    R.anim.fade_in,
                    R.anim.bottom_out
                )
            }
            TransitionDirection.FADE -> {
                setCustomAnimations(
                    R.anim.fade_in,
                    R.anim.fade_out,
                    R.anim.fade_in,
                    R.anim.fade_out
                )
            }
        }
    }

    protected open fun showToast(@StringRes resId: Int, isLongTime: Boolean = false) {
        context?.let {
            Toast.makeText(it, resId, if (isLongTime) Toast.LENGTH_LONG else Toast.LENGTH_SHORT)
                .show()
        }
    }

    protected open fun onBackPress() {
        close()
    }

    protected fun close() {
        activity?.supportFragmentManager?.popBackStack()
    }
}

enum class TransitionDirection {
    RIGHT, BOTTOM, FADE
}