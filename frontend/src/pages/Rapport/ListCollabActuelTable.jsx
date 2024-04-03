import loadData from "../../model/utils/hooks.jsx";
import "./ListCollabActuelTable.css";
/**
 * @param  {Object}  props
 * @param  {number}  props.service
 * @param  {number}   props.month
 * @param  {number} props.year
 */

function getDaysInMonth(year, month) {
  const daysInMonth = new Date(year, month, 0).getDate();
  const days = [];
  for (let day = 1; day <= daysInMonth; day++) {
    days.push(day);
  }
  return days;
}
export default function ListCollabActuelTable({ service, month, year }) {
  const { loadedData } = loadData(
    `http://localhost:8082/api/absence/service?id=${service}&month=${month}&year=${year}`
  );

  const daysOfMonth = getDaysInMonth(year, month);
  let data = loadedData;
  const fullNameSet = new Set()
  const absenceDaysSet  = new Set()
  
  data.forEach((datas) => {
    fullNameSet.add(datas.firstName + " " + datas.lastName) 
    absenceDaysSet.add
  });
  console.log(data);
  console.log(month)
 
  return <>
  <h2>liste des congés</h2>
  <div id="table">
    <div className="user" >
      <h4>User</h4>
      <div className="conteneurUser">
      {Array.from(fullNameSet).map((fullName) => (
              <p key={fullName}>{fullName}</p>
            ))}
          </div>
        <div className="test">
        {daysOfMonth.map(day => (
          <p className="day" key={day}>{day}</p>
          ))}
        </div>
    </div>
    <div className="days">
    {daysOfMonth.map(day => (
          <p className="day" key={day}>{day}</p>
        ))}
        <div>

        </div>
    </div>

  </div>
  </>;
}
