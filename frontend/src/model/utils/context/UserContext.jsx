import { useState, createContext } from "react";

export const UserContext = createContext();

export default function UserContextProvider({ children }) {
  const [user, setCurrentUser] = useState({});
  // const [loadingData, setLoadingData] = useState(true)

  async function postData(url = "", donnees = {}) {
    const response = await fetch(url, {
      method: "POST",
      mode: "cors",
      cache: "no-cache",
      credentials: "same-origin",
      headers: {
        "Content-Type": "application/json",
      },
      redirect: "follow",
      referrerPolicy: "no-referrer",
      body: JSON.stringify(donnees), // le type utilisé pour le corps doit correspondre à l'en-tête "Content-Type"
    });

    return response.json();
  }


  const signIn = async (email, password) => {
    const response = await postData("http://localhost:8082/api/login", {
      email: email,
      password: password,
    });
    if (response.error) {
      throw new Error("Login or password incorrect");
    } else {
      console.log(response)
      localStorage.setItem("jwt", response.token)
      setCurrentUser(response.token);
      return true
    }
  };

  const logOut = async () => {
    // TODO: Request deletion for actual token
    localStorage.removeItem("jwt");
    setCurrentUser(null);
  };

  // useEffect(() => {
  // 	const unsubscribe = onAuthStateChanged(auth, (user) => {
  // 		setCurrentUser(user)
  // 		setLoadingData(false)
  // 	})

  // 	return unsubscribe
  // }, [])

  return (
    <UserContext.Provider value={{ user, signIn, logOut }}>
      {children}
    </UserContext.Provider>
    // <UserContext.Provider value={{ user, signIn, logOut }}>
    // 	{!loadingData && children}
    // </UserContext.Provider>
  );
}
