package com.example.tagebuch.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
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


public class ListarPensamientosEditarFragment extends Fragment implements PensamientosAdapter.ItemClickListener{


    private RecyclerView ListarPensamientosEditarrecyclerView;
    private MainActivityController mainActivityController;
    private PensamientosAdapter pensamientosAdapter;
    private DividerItemDecoration dividerItemDecoration;
    private LinearLayoutManager layoutManager;

    public ListarPensamientosEditarFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_listar_pensamientos_editar, container, false);

        ListarPensamientosEditarrecyclerView = view.findViewById(R.id.ListarPensamientosEditarRecycler);
        layoutManager = new LinearLayoutManager(getContext());
        dividerItemDecoration = new DividerItemDecoration(ListarPensamientosEditarrecyclerView.getContext(),layoutManager.getOrientation());

        ListarPensamientosEditarrecyclerView.addItemDecoration(dividerItemDecoration);
        ListarPensamientosEditarrecyclerView.setLayoutManager(layoutManager);
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