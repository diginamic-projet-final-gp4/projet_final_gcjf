import React, { useState, createContext } from "react";

const ThemeContext = createContext({ theme: "light" });

const ThemeProvider = ({ children }) => {
  const [theme, setTheme] = useState("light"); // Default theme

  const handleThemeChange = (newTheme) => {
    setTheme(newTheme);
  };

  return (
    <ThemeContext.Provider value={{ theme, handleThemeChange }}>
      {children}
    </ThemeContext.Provider>
  );
};

export default ThemeProvider;
