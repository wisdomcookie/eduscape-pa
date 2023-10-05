
import { PieChart } from 'react-minimal-pie-chart';
import PieChartCard from './Components/PieChartCard';
import SchoolCard from './Components/SchoolCard'
import SchoolListComponent from './Components/ComparisonScreen';



const Home: React.FC = () => {

    const chartContainerStyle = {
        display: 'flex',
        justifyContent: 'space-between',
        marginBottom: '10px',
      };

  const exampleSchoolInfo = {
    name: "Example School",
    graduationRate: 85,
    dropoutRate: 5,
    spendingPerStudent: 10000,
    facultyToStudentRatio: 10,
    avgTeacherEducationLevel: "4",
    percentile: 75
  };

  return (
    <div>
      <SchoolListComponent></SchoolListComponent>
    </div>
  );
};

export default Home;