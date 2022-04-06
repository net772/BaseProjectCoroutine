package com.example.baseproject.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import com.example.baseproject.R

abstract class BaseDialogFragment<Binding : ViewDataBinding> : DialogFragment() {

    private var _binding: Binding? = null
    protected val binding get() = _binding!!

    /* window 설정 값 */
    var windowWidth = ViewGroup.LayoutParams.MATCH_PARENT
    var windowHeight = ViewGroup.LayoutParams.WRAP_CONTENT

    var bottomSlideAnimation = false
    var canceledOnTouchOutside = true
    var dimBehind = false

    protected abstract fun createFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): Binding
    protected open fun initFragment() = Unit

    override fun onStart() {
        super.onStart()

        // 레이아웃 크기 및 위치 조정
        dialog?.apply {
            setCanceledOnTouchOutside(canceledOnTouchOutside)

            window?.apply {
                val gravity = if (bottomSlideAnimation) Gravity.BOTTOM else Gravity.CENTER
                setGravity(gravity)
                setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                setLayout(windowWidth, windowHeight)
                setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

                if (dimBehind) addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
                else clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = createFragmentBinding(inflater, container).apply {
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val styleAttr =
            if (bottomSlideAnimation) R.style.BottomSlideDialog else R.style.DialogNoTransition
        setStyle(STYLE_NO_TITLE, styleAttr)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
