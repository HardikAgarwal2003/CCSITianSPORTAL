package hardik.CCSITianSportal.HomePages.FacultyHomepage.FacultyFragments.RecyclerViewFiles;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

import hardik.CCSITianSportal.HomePages.FacultyHomepage.StudentDetails;
import hardik.CCSITianSportal.R;

public class RegistrationAdapter  extends RecyclerView.Adapter<RegistrationAdapter.MyViewHolder>{
    Context context;
    LayoutInflater lf;
    int numberOfEntries;
    ArrayList<String> listEnrollment = new ArrayList<String>();
    ArrayList<String> listStudentName = new ArrayList<String>();

    public RegistrationAdapter(Context context, ArrayList<String> listEnrollment, ArrayList<String> listPassword, int numberOfEntries){
        this.context = context;
        this.listEnrollment = listEnrollment;
        this.listStudentName = listPassword;
        this.numberOfEntries= numberOfEntries;
        lf = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RegistrationAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = lf.inflate(R.layout.student_row, parent, false);
        RegistrationAdapter.MyViewHolder myViewHolder = new MyViewHolder(v);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RegistrationAdapter.MyViewHolder holder, int position) {
        holder.txt_enrollment.setText(listEnrollment.get(position));
        holder.txt_studentName.setText(listStudentName.get(position));


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 17-12-2023 Do functionality for onClickListener...
                // getting email or student_name
                int student_idx = holder.getAdapterPosition(); // this is the index for the current selected item...
                Intent iStudentDetail = new Intent(v.getContext(), StudentDetails.class);
                iStudentDetail.putExtra("student_idx",student_idx); // sending the index using the intent...
                v.getContext().startActivity(iStudentDetail);

            }
        });
    }

    @Override
    public int getItemCount() {
        return numberOfEntries;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt_enrollment;
        TextView txt_studentName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_enrollment =itemView.findViewById(R.id.txt_enrollment);
            txt_studentName = itemView.findViewById(R.id.txt_name);
        }
    }
}
