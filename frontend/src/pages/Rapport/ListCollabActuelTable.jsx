import loadData from "../../model/utils/hooks.jsx"

/**
 * @param  {Object}  props  
 * @param  {number}  props.service 
 * @param  {number}   props.month
 * @param  {number} props.year
 */
export default function ListCollabActuelTable({service, month, year}) {
  const {loadedData}=loadData(`http://localhost:8082/api/absence/service?id=${service}&month=${month}&year=${year}`)

   let data = loadedData;
   console.log(data)
  return(
  <>
  </>
  )
}
