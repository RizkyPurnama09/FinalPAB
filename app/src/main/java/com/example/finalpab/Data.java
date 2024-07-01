package com.example.finalpab;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Data extends AppCompatActivity {
    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private List<User> userList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        userAdapter = new UserAdapter(userList, this);
        recyclerView.setAdapter(userAdapter);

        // Set the MainActivity reference in the adapter
        userAdapter.setData(this);

        findViewById(R.id.button_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddUserDialog();
            }
        });

        fetchUsers();
    }

    private void showAddUserDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Tambah Data");

        View view = getLayoutInflater().inflate(R.layout.dialog_add_user, null);
        final EditText editTextNik = view.findViewById(R.id.editTextNik);
        final EditText editTextNama = view.findViewById(R.id.editTextNama);
        final EditText editTextAlamat = view.findViewById(R.id.editTextAlamat);
        final EditText editTextJk = view.findViewById(R.id.editTextJk);
        final EditText editTextAgama = view.findViewById(R.id.editTextAgama);

        builder.setView(view);
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String nik = editTextNik.getText().toString();
                String nama = editTextNama.getText().toString();
                String alamat = editTextAlamat.getText().toString();
                String jk = editTextJk.getText().toString();
                String agama = editTextAgama.getText().toString();
                addUser(nik, nama, alamat, jk, agama);
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.create().show();
    }

    private void addUser(String nik, String nama, String alamat, String jk, String agama) {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        User user = new User(nik, nama, alamat, jk, agama);
        Call<Void> call = apiService.insertUser(user);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(Data.this, "Berhasil Tambah Data", Toast.LENGTH_SHORT).show();
                    fetchUsers();
                } else {
                    Toast.makeText(Data.this, "Gagal Tambah Data: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(Data.this, "Gagal Menambahkan", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void fetchUsers() {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<List<User>> call = apiService.getUsers();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()) {
                    userList.clear();
                    userList.addAll(response.body());
                    userAdapter.notifyDataSetChanged();
                } else {
                    Log.e("MainActivity", "Response error: " + response.errorBody().toString());
                    Toast.makeText(Data.this, "Gagal Mengambil Data: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e("MainActivity", "Fetch error: ", t);
                Toast.makeText(Data.this, "Gagal Mengambil Data: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateUser(int id, String nik, String nama, String alamat, String jk, String agama) {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        User user = new User(id, nik, nama, alamat, jk, agama);
        Call<Void> call = apiService.updateUser(user);

        Log.d("MainActivity", "Updating user: " + id + ", " + nik + ", " + nama+ ", " + alamat+ ", " + jk+ ", " + agama);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.d("MainActivity", "User updated successfully");
                    Toast.makeText(Data.this, "Data Berhasil di Update", Toast.LENGTH_SHORT).show();
                    fetchUsers();
                } else {
                    Log.e("MainActivity", "Response error: " + response.errorBody().toString());
                    Toast.makeText(Data.this, "Gagal Update Data: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("MainActivity", "Fetch error: ", t);
                Toast.makeText(Data.this, "Gagal Update Data: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void showUpdateDialog(final User user) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Update Data");

        View viewInflated = LayoutInflater.from(this).inflate(R.layout.dialog_update_user, (ViewGroup) findViewById(android.R.id.content), false);
        final EditText inputNik = viewInflated.findViewById(R.id.editTextNik);
        final EditText inputNama = viewInflated.findViewById(R.id.editTextNama);
        final EditText inputAlamat = viewInflated.findViewById(R.id.editTextAlamat);
        final EditText inputJk = viewInflated.findViewById(R.id.editTextJk);
        final EditText inputAgama = viewInflated.findViewById(R.id.editTextAgama);

        inputNik.setText(user.getNik());
        inputNama.setText(user.getNama());
        inputAlamat.setText(user.getAlamat());
        inputJk.setText(user.getJk());
        inputAgama.setText(user.getAgama());

        builder.setView(viewInflated);

        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                String nik = inputNik.getText().toString();
                String nama = inputNama.getText().toString();
                String alamat = inputAlamat.getText().toString();
                String jk = inputJk.getText().toString();
                String agama = inputAgama.getText().toString();
                updateUser(user.getId(), nik, nama, alamat, jk, agama);
            }
        });

        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    public void refreshData() {
        fetchUsers();
    }
}
