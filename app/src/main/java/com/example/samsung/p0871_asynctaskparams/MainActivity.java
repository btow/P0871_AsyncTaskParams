package com.example.samsung.p0871_asynctaskparams;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private TextView tvInfo;

    private class MyTask extends AsyncTask<String, Integer, Void> {

        protected void onPreExecute() {
            super.onPreExecute();
            tvInfo.setText(R.string.begin);
        }

        @Override
        protected Void doInBackground(String... params) {
            try {
                int i = 0;
                for (String url : params) {
                    //Загрузка файла
                    downloadFile(url);
                    //Вывод промежуточных результатов
                    publishProgress(++i);
                }
                //Эмуляция разъединения
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            tvInfo.setText("Downloaded " + values[0] + " files.");
        }

        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            tvInfo.setText("End");
        }

        private void downloadFile(String url) throws InterruptedException {
            //Эмуляция загрузки файла
            TimeUnit.SECONDS.sleep(2);
        }

    }

    private MyTask myTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInfo = (TextView) findViewById(R.id.tvInfo);
    }

    public void onClickButton(View view) {
        myTask = new MyTask();
        myTask.execute("file_path_1", "file_path_2", "file_path_3", "file_path_4");
    }
}
