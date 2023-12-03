import { useRouter } from 'next/router';
import React from 'react';
import DetailedSchoolPage from '../Components/DetailedSchoolPage'; // Import SchoolCard component and SchoolInfo type
import { Checkbox, FormControlLabel, Typography } from '@mui/material';
import Header from '../Components/Header';

const SchoolPage = () => {
  const router = useRouter();
  const { schoolName } = router.query;

  return (
    <div style = {{backgroundColor: '#F2E3DB'}}>
      <Header></Header>
      {schoolName 
        ? <DetailedSchoolPage schoolName={schoolName as String}></DetailedSchoolPage> 
        : <div>Invalid route</div>}
    </div>
  );
};

export default SchoolPage;
