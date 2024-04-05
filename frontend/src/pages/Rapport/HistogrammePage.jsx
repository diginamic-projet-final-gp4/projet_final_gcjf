import { useState } from "react";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import Histogramme from "../../components/Histogramme/Histogramme";

import "./Rapport.css";
import useFetchData from "../../model/utils/hooks";

export default function HistogrammePage() {
  const { loadedData: services } = useFetchData(
    "http://localhost:8082/api/service/all"
  );
  const [selectedDate, setSelectedDate] = useState(new Date());
  const [serviceId, setServiceId] = useState(1);
  const [year, setYear] = useState(new Date().getFullYear());
  const [month, setMonth] = useState(3);

  const handleDateChange = (date) => {
    setSelectedDate(date);
    setMonth(new Date(date).getMonth());
    setYear(new Date(date).getFullYear());
  };

  const handleChangeService = (e) => {
    setServiceId(e.target.value);
  };

  return (
    <>
      <h1>Histogramme</h1>
      <div className="histogramme">
        <div>
          <label htmlFor="service">Service:</label>
          <select
            id="service"
            name="service"
            required
            onChange={handleChangeService}
          >
            {services.map((service) => (
              <option key={service.id} value={service.id}>
                {service.lastName}
              </option>
            ))}
          </select>
        </div>
        <DatePicker
          selected={selectedDate}
          onChange={handleDateChange}
          dateFormat="MM/yyyy"
        />
        <Histogramme
          selectedService={serviceId}
          selectedMonth={month}
          selectedYear={year}
        />
      </div>
    </>
  );
}
