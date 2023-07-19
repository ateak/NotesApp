package ru.study.notesapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import ru.study.notesapp.databinding.FragmentMainBinding

class MainFragment : Fragment(), Contract.View {

    private lateinit var bindingMain: FragmentMainBinding
    private lateinit var adapter: CustomRecyclerAdapter
    private val dataModel: DataModel by activityViewModels()
    private val presenter = Presenter(this, Model())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        bindingMain = FragmentMainBinding.inflate(inflater)
        Log.v("MainFragment", "Main fragment on create")
        return bindingMain.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        context?.let { presenter.showNotes(it) }
    }

    override fun initViews() {
        initRecyclerView()
        initButtons()
    }

    private fun initRecyclerView() {
        adapter = CustomRecyclerAdapter { note ->
            openFragment(DetailsNoteFragment.newInstance(), R.id.fragmentContainerView2)
            dataModel.hash.value = note.hashCode()
        }
        bindingMain.recyclerView.adapter = adapter
    }

    private fun initButtons() {
        bindingMain.noteButton.let {
            it.setOnClickListener {
                openFragment(CreateNoteFragment.newInstance(), R.id.fragmentContainerView2)
            }
        }
    }

    override fun updateViews() {
        adapter.updateAdapter(presenter.getNotes())
    }

    override fun openFragment(fragment: Fragment, idHolder: Int) {
        parentFragmentManager
            .beginTransaction()
            .addToBackStack("MainFragment")
            .replace(idHolder, fragment)
            .commit()
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}