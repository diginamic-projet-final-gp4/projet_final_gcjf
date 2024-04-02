import { useEffect, useState } from "react";
import loadData from "../../model/utils/hooks.jsx"

export default function ListCollabActuel() {
  const { loadedData } = loadData("http://localhost:8082/api/service/all")
  //const { loadedData2 } = loadData("http://localhost:8082/api/absence/service?id=1&month=3&year=2024")
  const [services, setServices] = useState([]);
  const [months] = useState([
    { value: 1, label: "January" },
    { value: 2, label: "February" },
    { value: 3, label: "March" },
    { value: 4, label: "April" },
    { value: 5, label: "May" },
    { value: 6, label: "June" },
    { value: 7, label: "July" },
    { value: 8, label: "August" },
    { value: 9, label: "September" },
    { value: 10, label: "October" },
    { value: 11, label: "November" },
    { value: 12, label: "December" },
    // Ajoutez les autres mois ici
  ]);
  const [years] = useState([2022, 2023, 2024, 2025, 2026, 2027, 2028, 2029, 2030]);

  
  useEffect(() => {
    console.log(loadedData)
    setServices(loadedData)

  },[loadedData]) 

  const handleFormSubmit = (e) => {
    e.preventDefault();
    const selectedService = e.target.service.value;
    const selectedMonth = e.target.month.value;
    const selectedYear = e.target.year.value;

    console.log(selectedService + selectedMonth + selectedYear)
    const {loadedData} = loadData(`http://localhost:8082/api/absence/service?id=${selectedService}&month=${selectedMonth}&year=${selectedYear}`)
    console.log(loadedData)
    
    // fetchAbsences(selectedService, selectedMonth, selectedYear);
  };
  return (
    <>
      <h1>Vue par département et par jour</h1>
      <form onSubmit={handleFormSubmit}>
        <div>
          <label htmlFor="service">Service:</label>
          <select id="service" name="service">
            {services.map((service) => (
              <option key={service.id} value={service.id}>{service.lastName}</option>
            ))}
          </select>
        </div>
        <div>
          <label htmlFor="month">Mois:</label>
          <select id="month" name="month">
            {months.map((month) => (
              <option key={month.value} value={month.value}>{month.label}</option>
            ))}
          </select>
        </div>
        <div>
          <label htmlFor="year">Année:</label>
          <select id="year" name="year">
            {years.map((year) => (
              <option key={year} value={year}>{year}</option>
            ))}
          </select>
        </div>
        <button type="submit">Rechercher</button>
      </form>
    </>
  );
}
