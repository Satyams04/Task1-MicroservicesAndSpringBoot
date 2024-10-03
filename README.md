# Task1-MicroservicesAndSpringBoot

## Services Description Hosted on AWS Elastic Beanstalk
1. First Microservice exposing two methods AWS hosted URL ---> http://serviceaelasticbeanstalk-env.eba-pjujwm3t.eu-north-1.elasticbeanstalk.com
  a) GET method service endpoint which is used to return "UP" when service is up ---> /api/v1/checkStatus
  b) POST method service endpoint which is used to concatenate responses of the Get call of Service 2 and the Post call of Service 3 ---> /api/v1/concatenateServices
3. Second Microservice exposing one get method AWS hosted URL ---> http://servicebelasticbeanstalk-env.eba-mhyji35i.eu-north-1.elasticbeanstalk.com
  a) GET method endpoint which is called by the first service to fetch a string "Hello" ---> /api/v1/hello
4. Third Microservice exposing one get method AWS hosted URL ---> http://servicecelasticbeanstalk-env.eba-tt2ukibi.eu-north-1.elasticbeanstalk.com
  a) POST method endpoint which is called by first service to print/log the passed json and return the concatenated name elements as a string --> /api/v1/process
5. First Microsrervice Swagger UI exposed AWS URL endpoint ---> http://serviceaelasticbeanstalk-env.eba-pjujwm3t.eu-north-1.elasticbeanstalk.com/swagger-ui/index.html
7. Error Handling is done via creating Custom Exception Class and Global Exception Handler
8. Logger is performed using Spring AOP mechnaism and concatenated Trace ID while printing it.
