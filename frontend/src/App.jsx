import React, { useState, createContext, useContext } from "react";
import UserContextProvider from "./model/utils/context/UserContext";
import Body from "./components/Body/Body";

import "./App.css";

const ThemeContext = createContext({ theme: "light" });

const ThemeProvider = ({ children }) => {
  const [theme, setTheme] = useState("light");

  const handleThemeChange = (newTheme) => {
    setTheme(newTheme);
  };

  return (
    <ThemeContext.Provider value={{ theme, handleThemeChange }}>
      {children}
    </ThemeContext.Provider>
  );
};

function App() {
  let theme = localStorage.getItem("theme");

  return (
    <ThemeProvider>
      <UserContextProvider>
        <div className={`${theme}`}>
          <Body />
        </div>
      </UserContextProvider>
    </ThemeProvider>
  );
}

export default App;
