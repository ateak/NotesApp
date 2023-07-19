package ru.study.notesapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.study.notesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    //private val dataModel: DataModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        openFragment(MainFragment.newInstance(), R.id.fragmentContainerView2)
    }

    private fun openFragment(fragment: Fragment, idHolder: Int) {
        supportFragmentManager
            .beginTransaction()
            .replace(idHolder, fragment)
            .commit()
    }

    /**
     * Функция для удаления заметки по свайпу вправо
     */
    private fun swapHelper(): ItemTouchHelper {
        return ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                adapter.removeItem(viewHolder.bindingAdapterPosition)

            }
        })
    }
}
