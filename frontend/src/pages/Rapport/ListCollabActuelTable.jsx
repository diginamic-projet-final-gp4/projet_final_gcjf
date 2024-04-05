import loadData from "../../model/utils/hooks.jsx";
import PropTypes from "prop-types";
import "./Rapport.css";

ListCollabActuelTable.propTypes = {
  service: PropTypes.number.isRequired,
  month: PropTypes.number.isRequired,
  year: PropTypes.number.isRequired,
};

/**
 * @param  {Object}  props
 * @param  {number}  props.service
 * @param  {number}  props.month
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

  data.forEach((user) => {
    let obj = {};
    for (let i = 0; i < daysOfMonth.length; i++) {
      let dayOfMonth = new Date(year, month, i);
      for (let abs of user.absences) {
        let dayOfUser = new Date(abs.dtDebut);
        if (dayOfUser.getDate() == dayOfMonth.getDate()) {
          if (abs.type == "PAID_LEAVE" || abs.type == "UNPAID_LEAVE") {
            abs.type = "C";
          }
          if (abs.type == "RTT_EMPLOYER" || abs.type == "RTT_EMPLOYEE") {
            abs.type = "R";
          }
          if (abs.type == "FERIEE") {
            abs.type = "F";
          }
          obj[dayOfMonth] = abs.type;
        } else continue;
      }
      if (obj[dayOfMonth] == undefined) {
        obj[dayOfMonth] = null;
      }
    }
    user["obj"] = obj;
  });

  return (
    <>
      <h2>liste des congés</h2>
      <div id="table">
        <table>
          <thead>
            <tr>
              <th className="user">User</th>
              {daysOfMonth &&
                daysOfMonth.map((val) => <th key={val}>{val}</th>)}
            </tr>
          </thead>
          <tbody>
            {data &&
              data.map((user, index) => {
                return (
                  <tr key={index}>
                    <td>{user.firstName + " " + user.lastName}</td>
                    {Array.from(Object.keys(user?.obj)).map((item, i) => {
                      if (user.obj[item])
                        return <td key={i}>{user.obj[item].split("")[0]}</td>;
                      else return <td key={i}></td>;
                    })}
                  </tr>
                );
              })}
          </tbody>
        </table>
      </div>
    </>
  );
}
