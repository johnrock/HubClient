package com.jpiser.hubclient.presentation.features.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jpiser.hubclient.R;
import com.jpiser.hubclient.presentation.features.main.model.MenuItem;

import java.util.List;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public class MainRecyclerViewAdapter  extends RecyclerView.Adapter<MainRecyclerViewAdapter.MenuItemHolder>{

    public interface MenuItemTapper{
        void tapItem(MenuItem menuItem);
    }


    private List<MenuItem> menuItems;
    private MenuItemTapper menuItemTapper;

    public MainRecyclerViewAdapter(List<MenuItem> menuItems, MenuItemTapper menuItemTapper) {
        this.menuItems = menuItems;
        this.menuItemTapper = menuItemTapper;
    }

    @Override
    public MenuItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feature_main_menuitem, parent, false);
        return new MenuItemHolder(view, menuItemTapper);
    }

    @Override
    public void onBindViewHolder(MenuItemHolder holder, int position) {
        holder.bindMenuItem(menuItems.get(position));
    }

    @Override
    public int getItemCount() {
        return menuItems.size();
    }

    public static class MenuItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView nameTextView;
        private final MenuItemTapper menuItemTapper;
        private MenuItem menuItem;

        public MenuItemHolder(View itemView, MenuItemTapper menuItemTapper) {
            super(itemView);
            this.menuItemTapper = menuItemTapper;
            nameTextView = (TextView) itemView.findViewById(R.id.name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            menuItemTapper.tapItem(menuItem);

        }

        public void bindMenuItem(MenuItem menuItem) {
            this.menuItem = menuItem;
            nameTextView.setText(menuItem.getName());
        }
    }
}
