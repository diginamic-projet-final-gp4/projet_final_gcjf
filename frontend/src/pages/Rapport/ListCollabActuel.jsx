import { useEffect, useState } from "react";
import loadData from "../../model/utils/hooks.jsx"
import ListCollabActuelTable from "./ListCollabActuelTable.jsx";

const months = [
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
];

const years =[2022, 2023, 2024, 2025, 2026, 2027, 2028, 2029, 2030];
export default function ListCollabActuel() {
  //setServices(loadData(`http://localhost:8082/api/absence/service?id=${selectedService}&month=${selectedMonth}&year=${selectedYear}`))
  const { loadedData : services} = loadData("http://localhost:8082/api/service/all")
  const [month, setMonth] = useState(months[0].value)
  const [year, setYear] = useState(years[0])
  const [service, setService ] = useState(null)

  const handleChangeService = e => setService(e.target.value)
  const handleChangeMonth = e => setMonth(e.target.value)
  const handleChangeYear = e => setYear(e.target.value)

  useEffect(() => {
    console.log(services)
    setService(services[0]?.id)
  },[services])
  return (
    <>
      <h1>Vue par département et par jour</h1>
      <form>
        <div>
          <label htmlFor="service">Service:</label>
          <select id="service" name="service" required onChange={handleChangeService} >
            {services.map((service) => (
              <option key={service.id} value={service.id}>{service.lastName}</option>
            ))}
          </select>
        </div>
        <div>
          <label htmlFor="month">Mois:</label>
          <select id="month" name="month" value={month} onChange={handleChangeMonth}>
            {months.map((month) => (
              <option key={month.value} value={month.value}>{month.label}</option>
            ))}
          </select>
        </div>
        <div>
          <label htmlFor="year">Année:</label>
          <select id="year" name="year" onChange={handleChangeYear}>
            {years.map((year) => (
              <option key={year} value={year}>{year}</option>
            ))}
          </select>
        </div>
      </form>
      {service != null && (<ListCollabActuelTable service={service} month={month} year={year}/>)}
    </>
  );
}
