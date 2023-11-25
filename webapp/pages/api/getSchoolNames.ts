import { NextApiRequest, NextApiResponse } from 'next';

interface SchoolInfo {
    name: string;
    graduationRate: number;
    graduationRatePercentile: number;
    dropoutRate: number;
    dropoutRatePercentile: number;
    spendingPerStudent: number;
    spendingPerPercentile: number;
    facultyToStudentRatio: number;
    facultyToStudentRatioPercentile: number;
    avgTeacherEducationLevel: string;
    avgTeacherEducationLevelPercentile: string;
    percentile: number; // Use strings for education level codes
  }
  
  const sampleSchoolData: SchoolInfo[] = [
    {
      name: "Sample School 1",
      graduationRate: 90,
      graduationRatePercentile: 85,
      dropoutRate: 3,
      dropoutRatePercentile: 70,
      spendingPerStudent: 12000,
      spendingPerPercentile: 60,
      facultyToStudentRatio: 12,
      facultyToStudentRatioPercentile: 75,
      avgTeacherEducationLevel: "5",
      avgTeacherEducationLevelPercentile: "80",
      percentile: 85,
    },
    {
      name: "Sample School 2",
      graduationRate: 80,
      graduationRatePercentile: 60,
      dropoutRate: 6,
      dropoutRatePercentile: 30,
      spendingPerStudent: 9000,
      spendingPerPercentile: 40,
      facultyToStudentRatio: 8,
      facultyToStudentRatioPercentile: 45,
      avgTeacherEducationLevel: "3",
      avgTeacherEducationLevelPercentile: "35",
      percentile: 65,
    },
    {
      name: "Sample School 3",
      graduationRate: 85,
      graduationRatePercentile: 75,
      dropoutRate: 5,
      dropoutRatePercentile: 40,
      spendingPerStudent: 10000,
      spendingPerPercentile: 50,
      facultyToStudentRatio: 10,
      facultyToStudentRatioPercentile: 55,
      avgTeacherEducationLevel: "4",
      avgTeacherEducationLevelPercentile: "50",
      percentile: 75,
    },
    {
      name: "Sample School 4",
      graduationRate: 95,
      graduationRatePercentile: 90,
      dropoutRate: 2,
      dropoutRatePercentile: 90,
      spendingPerStudent: 15000,
      spendingPerPercentile: 80,
      facultyToStudentRatio: 15,
      facultyToStudentRatioPercentile: 90,
      avgTeacherEducationLevel: "6",
      avgTeacherEducationLevelPercentile: "95",
      percentile: 95,
    },
    {
      name: "Sample School 5",
      graduationRate: 75,
      graduationRatePercentile: 50,
      dropoutRate: 8,
      dropoutRatePercentile: 20,
      spendingPerStudent: 8000,
      spendingPerPercentile: 30,
      facultyToStudentRatio: 7,
      facultyToStudentRatioPercentile: 30,
      avgTeacherEducationLevel: "2",
      avgTeacherEducationLevelPercentile: "20",
      percentile: 55,
    },
    {
      name: "Sample School 6",
      graduationRate: 70,
      graduationRatePercentile: 40,
      dropoutRate: 10,
      dropoutRatePercentile: 10,
      spendingPerStudent: 7000,
      spendingPerPercentile: 20,
      facultyToStudentRatio: 6,
      facultyToStudentRatioPercentile: 20,
      avgTeacherEducationLevel: "1",
      avgTeacherEducationLevelPercentile: "10",
      percentile: 45,
    },
    {
      name: "Sample School 7",
      graduationRate: 88,
      graduationRatePercentile: 80,
      dropoutRate: 4,
      dropoutRatePercentile: 60,
      spendingPerStudent: 11000,
      spendingPerPercentile: 55,
      facultyToStudentRatio: 11,
      facultyToStudentRatioPercentile: 65,
      avgTeacherEducationLevel: "5",
      avgTeacherEducationLevelPercentile: "75",
      percentile: 80,
    },
    {
      name: "Sample School 8",
      graduationRate: 82,
      graduationRatePercentile: 70,
      dropoutRate: 7,
      dropoutRatePercentile: 50,
      spendingPerStudent: 9500,
      spendingPerPercentile: 45,
      facultyToStudentRatio: 9,
      facultyToStudentRatioPercentile: 50,
      avgTeacherEducationLevel: "4",
      avgTeacherEducationLevelPercentile: "45",
      percentile: 70,
    },
    {
      name: "Sample School 9",
      graduationRate: 78,
      graduationRatePercentile: 55,
      dropoutRate: 9,
      dropoutRatePercentile: 25,
      spendingPerStudent: 8500,
      spendingPerPercentile: 35,
      facultyToStudentRatio: 8,
      facultyToStudentRatioPercentile: 40,
      avgTeacherEducationLevel: "3",
      avgTeacherEducationLevelPercentile: "30",
      percentile: 60,
    },
    {
      name: "Sample School 10",
      graduationRate: 92,
      graduationRatePercentile: 95,
      dropoutRate: 2,
      dropoutRatePercentile: 90,
      spendingPerStudent: 13000,
      spendingPerPercentile: 70,
      facultyToStudentRatio: 14,
      facultyToStudentRatioPercentile: 80,
      avgTeacherEducationLevel: "6",
      avgTeacherEducationLevelPercentile: "85",
      percentile: 90,
    },
  ];

const getSchoolNames = (req: NextApiRequest, res: NextApiResponse) => {
  try {
    const names = sampleSchoolData.map((school) => school.name);
    res.status(200).json(names);
  } catch (error) {
    console.error('Error fetching school names:', error);
    res.status(500).json({ error: 'Internal Server Error' });
  }

  
};

export default getSchoolNames;
