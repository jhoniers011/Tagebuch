package com.example.tagebuch.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tagebuch.Adapter.CategoriasAdapter;
import com.example.tagebuch.Adapter.PensamientosAdapter;
import com.example.tagebuch.R;
import com.example.tagebuch.controller.MainActivityController;
import com.example.tagebuch.model.pojo.Categoria;
import com.example.tagebuch.model.pojo.Pensamiento;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListarPensamientosEditarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListarPensamientosEditarFragment extends Fragment implements PensamientosAdapter.ItemClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView ListarPensamientosEditarrecyclerView;
    private MainActivityController mainActivityController;
    private PensamientosAdapter pensamientosAdapter;

    public ListarPensamientosEditarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListarPensamientosEditar.
     */
    // TODO: Rename and change types and number of parameters
    public static ListarPensamientosEditarFragment newInstance(String param1, String param2) {
        ListarPensamientosEditarFragment fragment = new ListarPensamientosEditarFragment();
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
        View view = inflater.inflate(R.layout.fragment_listar_pensamientos_editar, container, false);

        ListarPensamientosEditarrecyclerView = view.findViewById(R.id.ListarPensamientosEditarRecycler);
        ListarPensamientosEditarrecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mainActivityController = new MainActivityController();

        List<Pensamiento> listapensamientos;

        listapensamientos = ListarPensamientos(mainActivityController);

        pensamientosAdapter = new PensamientosAdapter(listapensamientos,getContext(),this);
        ListarPensamientosEditarrecyclerView.setAdapter(pensamientosAdapter);


        return view;
    }


    public List<Pensamiento> ListarPensamientos(MainActivityController mainActivityController){

        return mainActivityController.listarPensamiento(getActivity());

    }

    @Override
    public void OnItemClick(Pensamiento pensamiento) {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        Fragment fragment = new EditarPensamientoFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable("pensamiento",pensamiento);
        fragment.setArguments(bundle);
        ft.replace(R.id.ChangeFrameLayout,fragment);
        ft.addToBackStack(null);
        ft.commit();



    }
}