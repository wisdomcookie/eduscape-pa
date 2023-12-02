import { useRouter } from 'next/router';
import React, { useEffect, useState } from 'react';
import { SchoolInfo } from '../Components/SchoolCard'; // Import SchoolCard component and SchoolInfo type
import DetailedSchoolPage from '../Components/DetailedSchoolPage'; // Import SchoolCard component and SchoolInfo type
import { Checkbox, FormControlLabel, Typography } from '@mui/material';

const exampleSchoolInfo: SchoolInfo = {
    name: "Example School",
    graduationRate: 85,
    graduationRatePercentile: 85,
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


const SchoolPage = () => {
  const router = useRouter();
  const { schoolName } = router.query;

  const [schoolInfo, setSchoolInfo] = useState<SchoolInfo>(exampleSchoolInfo);
  const [error, setError] = useState<string | null>(null);

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
        setSchoolInfo(exampleSchoolInfo);
      }
    } catch (error) {
      console.error('Error fetching data:', error);
      setSchoolInfo(exampleSchoolInfo);
      setError('Error fetching data');
    }
  };
  
  fetchSchoolInfo();
  // Fetch school data based on the ID
  // For example, using fetch() or a library like axios

  return (
    <div>
      <DetailedSchoolPage schoolInfo={schoolInfo}></DetailedSchoolPage>
    </div>
  );
};

export default SchoolPage;
