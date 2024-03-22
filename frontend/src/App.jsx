import "./App.css";

import UserContextProvider from "./model/utils/context/UserContext";
import Body from "./components/Body/Body";

function App() {
  return (
    <UserContextProvider>
      <Body />
    </UserContextProvider>
  );
}

export default App;
