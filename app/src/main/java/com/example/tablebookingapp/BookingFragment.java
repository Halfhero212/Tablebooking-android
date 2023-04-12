package com.example.tablebookingapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import java.util.Calendar;

public class BookingFragment extends Fragment {

    private EditText dateInput;
    private EditText timeInput;

    public BookingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_booking, container, false);

        Button bookingButton = view.findViewById(R.id.booking_button);
        bookingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInputs()) {
                    Navigation.findNavController(v).navigate(R.id.action_bookingFragment_to_confirmationFragment);
                } else {
                    Toast.makeText(getContext(), "Please fill in both date and time.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button showMenuButton = view.findViewById(R.id.show_menu_button);
        showMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_bookingFragment_to_menuFragment);
            }
        });

        dateInput = view.findViewById(R.id.date_input);
        dateInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        timeInput = view.findViewById(R.id.time_input);
        timeInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
            }
        });

        return view;
    }

    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                dateInput.setText(String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth));
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    private void showTimePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                timeInput.setText(String.format("%02d:%02d", hourOfDay, minute));
            }
        }, hour, minute, true);
        timePickerDialog.show();
    }

    private boolean validateInputs() {
        String date = dateInput.getText().toString().trim();
        String time = timeInput.getText().toString().trim();

        return !date.isEmpty() && !time.isEmpty();
    }
}
