import { Route, Routes } from "react-router-dom";
import "./App.css";

import Home from "./Home";
import About from "./About";
import Dashboard from "./Dashboard";

function App() {
  return (
    <div className="App">
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="about" element={<About />} />
        <Route path="dashboard" element={<Dashboard />} />
      </Routes>
    </div>
  );
}

export default App;
