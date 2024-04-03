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
  const fullNameSet = new Set();
  const absenceDaysSet = new Set();

  data.forEach((datas) => {
    fullNameSet.add(datas.firstName + " " + datas.lastName);
    absenceDaysSet.add;
  });
  console.log(data);
  console.log(month);
  const maListe = [
    {
      fullName: "Jean mi",
      absences: [

      ]
    },
    {
      fullName: "qdfglkjhqsdg mi",
      absences: [

      ]
    },
    {
      fullName: "poiupoiu",
      absences: [
        
      ]
    },
  ];

  return (
    <>
      <h2>liste des congés</h2>
      <div id="table">
        <div className="user">
          <h4>User</h4>





          <table>
            <thead>
              <tr>
                <th>User</th>
                {daysOfMonth && daysOfMonth.map((val) => <th key={val}>{val}</th>)}
              </tr>
            </thead>
            <tbody>
              {
                maListe && maListe.map((user, index) => {
                  return <tr key={index}>
                    <td>{user.fullName}</td>
                    {user?.absences.map((absence) => {
                      <td>{}</td>
                    })}
                  </tr>
                })
              }


              <tr>
                <td>Jean mi</td>
                <td></td>
                <td>5</td>
                <td>48</td>
                <td></td>
                <td>654</td>
                <td>67</td>
                <td>67</td>
              </tr>


            </tbody>
          </table>
          
          
          {/* <div className="conteneurUser">
            {Array.from(fullNameSet).map((fullName) => (
              <p key={fullName}>{fullName}</p>
            ))}
          </div> */}
        </div>
        {/* <div className="days">
          {daysOfMonth.map((day) => (
            <p className="day" key={day}>
              {day}
            </p>
          ))}
          <div></div>
        </div> */}
      </div>
    </>
  );
}
