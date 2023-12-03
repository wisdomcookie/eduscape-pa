import { useEffect, useState } from "react";
import React from 'react'
import SchoolCard, { SchoolInfo } from './SchoolCard'; // Import SchoolCard component and SchoolInfo type
import BarChart from "./BarChart";

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
  avgTeacherSalary: 0,
  avgTeacherSalaryPercentile: 0
};

const defaultOptions = {
    dropoutRate: true,
    percentLowIncome: true,
    collegeBound: true,
    spendingPerStudent: true,
    facultyToStudentRatio: true,
    avgTeacherExperience: true,
    avgTeacherDegreeLevel: true,
    avgTeacherSalary: true,
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
    
const colors = ['#dbf2e3', '#e3dbf2', '#eaf2db', '#d0e9df', '#dbf2e3', '#e1c7e4', '#e4e1c7', '#1e90ff'];

const mappedYearlySchoolInfo = Array.from(yearlySchoolInfo.entries()).map(([year, data], index) => ({
  label: `School Year ${year}`,
  data: [
    Math.max(0, data.dropoutRatePercentile * 100),
    Math.max(0, data.percentLowIncomePercentile * 100),
    Math.max(0, data.collegeBoundPercentile * 100),
    Math.max(0, data.spendingPerPercentile * 100),
    Math.max(0, data.facultyToStudentRatioPercentile * 100),
    Math.max(0, data.avgTeacherExperiencePercentile * 100),
    Math.max(0, data.avgTeacherDegreeLevelPercentile * 100),
    Math.max(0, data.avgTeacherSalaryPercentile * 100),
  ],
  borderColor: colors[index % colors.length],
  backgroundColor: colors[(index + 1) % colors.length], // You can modify this to suit your needs
}));

    
    const xAxisLabels = [

      'Dropout Rate Percentile',
  
      'Percent Low Income Percentile',
  
      'College Bound Percentile',
  
      'Spending Per Student Percentile',
  
      'Faculty to Student Ratio Percentile',
  
      'Avg Teacher Experience Percentile',
  
      'Avg Teacher Degree Level Percentile',
  
      'Avg Teacher Salary Percentile',
    ];
  return (
    <>
      <h1 className="mx-auto mt-10 text-2xl font-semibold capitalize text-center">{`Detailed View: ${schoolName}`}</h1>
<h1 className="mx-auto mt-10 text-2xl font-semibold capitalize text-center">{`${schoolName}'s Percentiles over Time`}</h1>
<BarChart xAxisLabels={xAxisLabels} yAxisTitle={"Percentiles"} datasets={mappedYearlySchoolInfo} />



      <SchoolCard schoolInfo={schoolInfo} options={defaultOptions}></SchoolCard>
      <h1 className="mx-auto mt-10 mb-10 text-4xl font-semibold capitalize text-center">{`Yearly Data`}</h1>
      {Array.from(yearlySchoolInfo.entries())
        .map( ([year, info]) => (
          <>
            <h1 className="mx-auto mt-10 mb-10 text-4xl font-semibold capitalize text-center">{year}</h1>
            <SchoolCard schoolInfo={info} options={defaultOptions}></SchoolCard>
          </>
        ))}
    </>
  )
}

export default DetailedSchoolPage