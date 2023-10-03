
import SchoolCard from './Components/SchoolCard'



const Home: React.FC = () => {
  const exampleSchoolInfo = {
    name: "Example School",
    graduationRate: 85,
    dropoutRate: 5,
    spendingPerStudent: 10000,
    facultyToStudentRatio: 10,
    avgTeacherEducationLevel: "4",
    percentile: 75,
    overall_rating: 10,
  };

  return (
    <div style={{ display: 'flex', justifyContent: 'space-between' }}>
      <SchoolCard schoolInfo={exampleSchoolInfo} />
      <SchoolCard schoolInfo={exampleSchoolInfo} />
    </div>
  );
};

export default Home;