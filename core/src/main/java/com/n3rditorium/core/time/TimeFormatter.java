package com.n3rditorium.core.time;

import com.n3rditorium.core.time.settings.FormatRepository;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import static com.n3rditorium.core.time.settings.FormatRepository.TIMEZONE_BERLIN;

public class TimeFormatter {

   private final FormatRepository formatRepository;
   private SimpleDateFormat dateFormat;

   public TimeFormatter(FormatRepository formatRepository) {
      this.formatRepository = formatRepository;
   }

   public String formatNow() {
      return formatTime(Calendar.getInstance());
   }

   String formatTime(Calendar calendar) {
      if (calendar == null) {
         throw new IllegalArgumentException("calendar must not be null");
      }
      sanitize();
      dateFormat.setTimeZone(TimeZone.getTimeZone(TIMEZONE_BERLIN));
      return dateFormat.format(calendar.getTime());
   }

   private void sanitize() {
      if (dateFormat == null) {
         dateFormat =
               new SimpleDateFormat(formatRepository.getCurrentTimeFormat().second, Locale.GERMANY);
      }
   }
}
