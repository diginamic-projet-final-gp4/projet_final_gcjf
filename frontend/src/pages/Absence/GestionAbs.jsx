import { useState } from "react";
import Calendar from "react-calendar";

import "./GestionAbs.css";
import "react-calendar/dist/Calendar.css";

export default function SeeAbs() {
  const today = new Date();
  const minDate = new Date(
    today.getFullYear(),
    today.getMonth(),
    today.getDate()
  );
  const maxDate = new Date(
    today.getFullYear() + 1,
    today.getMonth(),
    today.getDate()
  );
  const [value, setValue] = useState(new Date());

  return (
    <>
      <h1>Gérer les absences</h1>
      <div>
        <ul>
          <li>
            <a href="/absence/create">Créer une absence</a>
          </li>
        </ul>
        <h2>Planning</h2>
        <div className="calendar">
          <Calendar
            onChange={setValue}
            value={value}
            minDate={minDate}
            maxDate={maxDate}
          />
        </div>
      </div>
    </>
  );
}
