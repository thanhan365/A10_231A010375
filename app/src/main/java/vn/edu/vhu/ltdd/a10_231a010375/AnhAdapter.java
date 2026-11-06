package vn.edu.vhu.ltdd.a10_231a010375;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class AnhAdapter extends RecyclerView.Adapter<AnhAdapter.AnhViewHolder> {

    public interface OnAnhClickListener {
        void onAnhClick(int viTri);
    }

    static class AnhViewHolder extends RecyclerView.ViewHolder {
        final ImageView imgAnh;
        final TextView tvChuThich;

        AnhViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAnh = itemView.findViewById(R.id.imgAnh);
            tvChuThich = itemView.findViewById(R.id.tvChuThich);
        }
    }

    private final List<Integer> anh;
    private final List<String> chuThich;
    private final OnAnhClickListener listener;

    public AnhAdapter(List<Integer> anh, List<String> chuThich, OnAnhClickListener listener) {
        this.anh = anh;
        this.chuThich = chuThich;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AnhViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_anh, parent, false);
        return new AnhViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AnhViewHolder holder, int position) {
        holder.imgAnh.setImageResource(anh.get(position));
        holder.tvChuThich.setText(chuThich.get(position));

        holder.itemView.setOnClickListener(v -> {
            int p = holder.getBindingAdapterPosition();
            if (p != RecyclerView.NO_POSITION) listener.onAnhClick(p);
        });

        holder.itemView.setOnLongClickListener(v -> {
            int p = holder.getBindingAdapterPosition();
            if (p != RecyclerView.NO_POSITION) {
                anh.remove(p);
                chuThich.remove(p);
                notifyItemRemoved(p); // Chạy hiệu ứng trượt mượt mà khi xóa ô
                return true;
            }
            return false;
        });
    }

    @Override
    public int getItemCount() {
        return anh.size();
    }
}