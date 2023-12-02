import { useState } from "react";
import React from 'react'
import SchoolCard, { SchoolInfo } from './SchoolCard'; // Import SchoolCard component and SchoolInfo type

interface SchoolPageProps {
  schoolInfo: SchoolInfo;
}

const DetailedSchoolPage: React.FC<SchoolPageProps>  = ({ schoolInfo }) => {
  
  return (
    <>
    <div>DetailedSchoolPage</div>
    <SchoolCard schoolInfo={schoolInfo}></SchoolCard>
    </>
  )
}

export default DetailedSchoolPage