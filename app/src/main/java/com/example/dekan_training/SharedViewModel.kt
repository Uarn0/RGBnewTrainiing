import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val _colors = MutableLiveData<List<String>>(emptyList())
    val colors: LiveData<List<String>> get() = _colors

    fun addColor(color: String) {
        val updatedList = _colors.value!!.toMutableList()
        updatedList.add(color)
        _colors.value = updatedList
    }
}
