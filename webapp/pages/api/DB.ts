// pages/api/educationInfo.ts

import { NextApiRequest, NextApiResponse } from 'next';

export interface SchoolInfo {
  name: string;
  graduationRate: number;
  dropoutRate: number;
  spendingPerStudent: number;
  facultyToStudentRatio: number;
  avgTeacherEducationLevel: string;
  percentile: number; // Use strings for education level codes
}
const sampleSchoolData = [
  {
    name: "Sample School 1",
    graduationRate: 90,
    dropoutRate: 3,
    spendingPerStudent: 12000,
    facultyToStudentRatio: 12,
    avgTeacherEducationLevel: "5",
    percentile: 85,
    overall_rating: 9,
  },
  {
    name: "Sample School 2",
    graduationRate: 80,
    dropoutRate: 6,
    spendingPerStudent: 9000,
    facultyToStudentRatio: 8,
    avgTeacherEducationLevel: "3",
    percentile: 65,
    overall_rating: 7,
  },
  {
    name: "Sample School 3",
    graduationRate: 85,
    dropoutRate: 5,
    spendingPerStudent: 10000,
    facultyToStudentRatio: 10,
    avgTeacherEducationLevel: "4",
    percentile: 75,
    overall_rating: 8,
  },
  {
    name: "Sample School 4",
    graduationRate: 95,
    dropoutRate: 2,
    spendingPerStudent: 15000,
    facultyToStudentRatio: 15,
    avgTeacherEducationLevel: "6",
    percentile: 95,
    overall_rating: 10,
  },
];
const schoolInfo: SchoolInfo[] = [
    {
      name: 'School A',
      graduationRate: 85,
      dropoutRate: 5,
      spendingPerStudent: 10000,
      facultyToStudentRatio: 10,
      avgTeacherEducationLevel: '4', // Bachelor's degree
      percentile: 75, // Added hypothetical percentile value
    },
    {
      name: 'School B',
      graduationRate: 90,
      dropoutRate: 3,
      spendingPerStudent: 12000,
      facultyToStudentRatio: 12,
      avgTeacherEducationLevel: '5', // Master's degree
      percentile: 85, // Added hypothetical percentile value
    },
    {
      name: 'School C',
      graduationRate: 80,
      dropoutRate: 6,
      spendingPerStudent: 9000,
      facultyToStudentRatio: 8,
      avgTeacherEducationLevel: '3', // Some college, less than bachelor's degree
      percentile: 65, // Added hypothetical percentile value
    },
    {
      name: 'School D',
      graduationRate: 95,
      dropoutRate: 2,
      spendingPerStudent: 15000,
      facultyToStudentRatio: 15,
      avgTeacherEducationLevel: '6', // Doctor's degree
      percentile: 95, // Added hypothetical percentile value
    }
  ];
  
  

export default (req: NextApiRequest, res: NextApiResponse) => {
  const { schoolName } = req.query;
  const school = sampleSchoolData.find(school => school.name === schoolName as string);

  if (!school) {
    res.status(404).json({ error: 'School not found' });
  } else {
    res.json(school);
  }
};
