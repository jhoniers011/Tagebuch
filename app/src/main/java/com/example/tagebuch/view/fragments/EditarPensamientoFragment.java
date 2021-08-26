package com.example.tagebuch.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tagebuch.Adapter.CategoriasAdapter;
import com.example.tagebuch.Adapter.PensamientosAdapter;
import com.example.tagebuch.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditarPensamientoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditarPensamientoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerListaPensamientos;
    private PensamientosAdapter adapterPensamientos;

    public EditarPensamientoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditarPensamientoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditarPensamientoFragment newInstance(String param1, String param2) {
        EditarPensamientoFragment fragment = new EditarPensamientoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_editar_pensamiento, container, false);

        recyclerListaPensamientos = view.findViewById(R.id.pensamientosRecycler);
        recyclerListaPensamientos.setLayoutManager(new LinearLayoutManager(getContext()));

        List<String> listapensamientos = new ArrayList<>();

        for(int i = 0; i<10;i++){
            listapensamientos.add("Hola"+i);
        }

        adapterPensamientos = new PensamientosAdapter(listapensamientos,getContext());
        recyclerListaPensamientos.setAdapter(adapterPensamientos);

        return view;
    }
}