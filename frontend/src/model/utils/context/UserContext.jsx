import { useState, createContext } from 'react';

export const UserContext = createContext();

export default function UserContextProvider({ children }) {
  const [user, setCurrentUser] = useState({})
  // const [loadingData, setLoadingData] = useState(true)

  async function postData(url = "", donnees = {}) {
    // Les options par défaut sont indiquées par *
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

    console.log(response);
    return response.json(); // transforme la réponse JSON reçue en objet JavaScript natif
  }

  const signIn = async (email, password) => {
    const response = await postData("http://localhost:8082/auth/login", {
      email: email,
      password: password,
    });
    setCurrentUser(response.token)
    localStorage.setItem("jwt", response.token)
   
  };
  // new Promise((resolve, reject) => {

  //   console.log(email, password);
  //   // signInWithEmailAndPassword(auth, email, password).then(resolve).catch(reject)
  // });

  // const logOut = async () => {
  // 	await signOut(auth)
  // 	setCurrentUser(null)
  // }

  // useEffect(() => {
  // 	const unsubscribe = onAuthStateChanged(auth, (user) => {
  // 		setCurrentUser(user)
  // 		setLoadingData(false)
  // 	})

  // 	return unsubscribe
  // }, [])

  return (
    <UserContext.Provider value={{ user, signIn }}>
      {children}
    </UserContext.Provider>
    // <UserContext.Provider value={{ user, signIn, logOut }}>
    // 	{!loadingData && children}
    // </UserContext.Provider>
  );
}
