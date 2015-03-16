package com.douniamastoura.macndour.douniamastourashop;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.douniamastoura.macndour.DmAdapters.ProductGridAdapter;

import java.util.ArrayList;


public class DmViewPagerProductListFragment extends Fragment implements AdapterView.OnItemClickListener {

    private String catTitle;
    private OnFragmentInteractionListener mListener;
    private ArrayList<String> productsList;
    private GridView productsGrid;
    private int currentPosition;
    int[] imageId = {
            R.drawable.cat_tous,
            R.drawable.cat_manteau,
            R.drawable.cat_abaya,
            R.drawable.cat_tunique,
            R.drawable.cat_jupe,
            R.drawable.cat_sarouel,
            R.drawable.cat_robe,
            R.drawable.cat_gilet,
            R.drawable.cat_bijoux

    };


    public DmViewPagerProductListFragment()
    {

    }

    public DmViewPagerProductListFragment(int position,ArrayList<String> productsList) {
        // Required empty public constructor
        this.productsList = productsList;
        this.currentPosition= position;


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dm_view_pager_product_list, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ProductGridAdapter adapter = new ProductGridAdapter(getActivity(), imageId, productsList);
        productsGrid=(GridView)getView().findViewById(R.id.gv_products_list);
        productsGrid.setOnItemClickListener(this);
        productsGrid.setAdapter(adapter);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intentDetail = new Intent(getActivity(),DmProductDetailActivity.class);
        intentDetail.putExtra("catTitle",catTitle);
        startActivity(intentDetail);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
