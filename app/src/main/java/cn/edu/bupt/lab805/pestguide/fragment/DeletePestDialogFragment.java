package cn.edu.bupt.lab805.pestguide.fragment;


import android.os.Bundle;
import android.app.DialogFragment;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import cn.edu.bupt.lab805.pestguide.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class DeletePestDialogFragment extends DialogFragment {

    private Button btnDelete;
    private Button btnCancel;
    private int index = -1;


    public DeletePestDialogFragment()
    {
        // Required empty public constructor
    }

    public interface DeletePestListener{
        void onPestDelete(int pos);
    }

    public void setIndex(int index){
        this.index = index;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.fragment_delete_pest_dialog, container, false);
        btnDelete = (Button) view.findViewById(R.id.btn_confirm_delete_pest);
        btnCancel = (Button) view.findViewById(R.id.btn_cancel_delete_pest);
        btnDelete.setOnClickListener(v -> {
            DeletePestListener listener = (DeletePestListener) getActivity();
            listener.onPestDelete(index);
            dismiss();
        });
        btnCancel.setOnClickListener(v -> dismiss());
        return view;
    }

}
