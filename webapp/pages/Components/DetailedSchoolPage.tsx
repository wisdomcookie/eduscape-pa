import { useEffect, useState } from "react";
import React from 'react'
import SchoolCard, { SchoolInfo } from './SchoolCard'; // Import SchoolCard component and SchoolInfo type

const YEARS = [2017, 2018, 2019, 2020, 2021, 2022];

const exampleSchoolInfo: SchoolInfo = {
    name: "Example School",
    dropoutRate: 5,
    dropoutRatePercentile: 30,
    percentLowIncome: 10,
    percentLowIncomePercentile: 30,
    collegeBound: 79,
    collegeBoundPercentile: 30,
    spendingPerStudent: 10000,
    spendingPerPercentile: 50,
    facultyToStudentRatio: 10,
    facultyToStudentRatioPercentile: 20,
    avgTeacherExperience: 6,
    avgTeacherExperiencePercentile: 90,
    avgTeacherDegreeLevel: 4,
    avgTeacherDegreeLevelPercentile: 70,
    percentile: 75,
    overall_rating: 10,
};

const defaultOptions = {
    dropoutRate: true,
    percentLowIncome: true,
    collegeBound: true,
    spendingPerStudent: true,
    facultyToStudentRatio: true,
    avgTeacherExperience: true,
    avgTeacherDegreeLevel: true,
}


interface SchoolPageProps {
    schoolName: String;
}

const DetailedSchoolPage: React.FC<SchoolPageProps>  = ({ schoolName }) => {
  
    const [schoolInfo, setSchoolInfo] = useState<SchoolInfo>(exampleSchoolInfo);
    const [yearlySchoolInfo, setYearlySchoolInfo] = useState<Map<Number, SchoolInfo>>(new Map());
    const [error, setError] = useState<string | null>(null);
    
    useEffect(() => { 
      const fetchSchoolInfo = async () => {
          try {
          
            const response = await fetch(`http://localhost:8080/schools/rates?name=${schoolName}`);
            console.log(response)
            if (response.ok) {
              const data = await response.json();
              console.log(data)
              setSchoolInfo(data);
              setError(null);
            } else {
              setError('School not found');
            }
          } catch (error) {
            console.error('Error fetching data:', error);
            setError('Error fetching data');
          }
      };


      const fetchYearlySchoolInfo = async() => {
        try {
          const yearlyInfoPromises = YEARS.map(async (year, index) => {
            const response = await fetch(`http://localhost:8080/schools/rates?name=${schoolName}&year=${year}`);
            const data: SchoolInfo = await response.json();
            return {year, data};
          });

          const yearlyInfo = await Promise.all(yearlyInfoPromises);

          const newYearlyInfo = new Map();
          yearlyInfo.forEach(({year, data}) => {
            newYearlyInfo.set(year, data)
          });

          setYearlySchoolInfo(newYearlyInfo);

        } catch (error) {
          console.log(error);
        }
          
      }
  
      if (schoolName) {
        fetchSchoolInfo();
        fetchYearlySchoolInfo();
      }

    }, [schoolName]);
  
    // Fetch school data based on the ID
    // For example, using fetch() or a library like axios
    
  return (
    <>
      <div>DetailedSchoolPage</div>
      <SchoolCard schoolInfo={schoolInfo} options={defaultOptions}></SchoolCard>
      {Array.from(yearlySchoolInfo.entries())
        .map( ([year, info]) => (
          <>
            {year}
            <SchoolCard schoolInfo={info} options={defaultOptions}></SchoolCard>
          </>
        ))}
    </>
  )
}

export default DetailedSchoolPage