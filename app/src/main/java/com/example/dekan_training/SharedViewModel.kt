import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dekan_training.MyColors

class SharedViewModel : ViewModel() {
    private val _colors = MutableLiveData<List<MyColors>>(emptyList())
    val colors: LiveData<List<MyColors>> get() = _colors

    fun addColor(color: String) {
        _colors.postValue(_colors.value?.plus(MyColors(color)))
    }
}