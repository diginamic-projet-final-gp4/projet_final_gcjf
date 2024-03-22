import './Login.css';
import { useRef, useState, useContext } from 'react';
import { useNavigate } from 'react-router-dom'

import { UserContext } from '../../model/utils/context/UserContext';


export default function Login() {
	const navigate = useNavigate()
    const formRef = useRef()
    const inputs = useRef([])
    const [validation, setValidation] = useState("")
	const { signIn } = useContext(UserContext)

    function resetAll() {
		inputs.current = []
	}

    const addInputs = el => {
		if (el && !inputs.current.includes(el)) {
			inputs.current.push(el)
		}
	}

    const handleForm = async (e) => {
		e.preventDefault()
		// if ((inputs.current[1].value.length || inputs.current[2].value.length) < 6) {
		// 	//pseudo validation côté front
		// 	setValidation("6 chars min")
		// 	return
		// }

		try {
			await signIn(
				inputs.current[0].value,
				inputs.current[1].value
			)

			// setValidation("")
			navigate("/admin/home")

		} catch (e) {
			// if(e.code === "auth/invalid-email") {
			// 	setValidation("Error in email")
			// }
			setValidation("Error, email or password incorrect")
		}

		resetAll()
	}

    return (   
        <>
            <div>Login</div> 
            <form ref={formRef} onSubmit={handleForm}>
                <label htmlFor='email'>
                    Email
                </label>
                <input ref={addInputs} type="email" name='email' />

                <label htmlFor='password'>
                    Password
                </label>
                <input ref={addInputs} type="password" name='password' />
                {
							validation !== "" && (
								<p>{validation}</p>
							)
						}
                <button type='submit'>Envoyer</button>
            </form> 
        </>
    )
}
