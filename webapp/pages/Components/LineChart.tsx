import { useEffect, useRef } from "react";
import Chart from "chart.js/auto";

interface LineChartProps {
  xAxisLabels: string[];
  yAxisTitle: string;
  dataLabel: string;
  data: number[];
}

function LineChart({ xAxisLabels, yAxisTitle, dataLabel, data }: LineChartProps) {
  const chartRef = useRef<Chart | null>(null);

  useEffect(() => {
    const canvas = document?.getElementById('myChart') as HTMLCanvasElement;
    const ctx = canvas?.getContext('2d');

    if (chartRef.current) {
      chartRef.current.destroy();
    }

    const myChart = new Chart(ctx!!, {
      type: 'line',
      data: {
        labels: xAxisLabels,
        datasets: [{
          data: data,
          label: dataLabel,
          borderColor: "#3e95cd",
          backgroundColor: "#7bb6dd",
          fill: false,
        }]
      },
      options: {
        scales: {
          x: {
            type: 'category',
            labels: xAxisLabels,
            title: {
              display: true,
              text: 'Year',
            },
          },
          y: {
            title: {
              display: true,
              text: yAxisTitle,
            },
          },
        },
      },
    });

    chartRef.current = myChart;
  }, [xAxisLabels, yAxisTitle, dataLabel, data]);

  return (
    <>
      {/* line chart */}
      <h1 className="w-[110px] mx-auto mt-10 text-xl font-semibold capitalize ">line Chart</h1>
      <div className="w-[1100px] h-screen flex mx-auto my-auto">
        <div className='border border-gray-400 pt-0 rounded-xl w-full h-fit my-auto shadow-xl'>
          <canvas id='myChart'></canvas>
        </div>
      </div>
    </>
  );
}

export default LineChart;
