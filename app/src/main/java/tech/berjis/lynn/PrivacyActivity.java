package tech.berjis.lynn;

import android.os.Bundle;
import android.text.Html;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PrivacyActivity extends AppCompatActivity {
    TextView agreementToTerms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_terms);

        agreementToTerms = findViewById(R.id.agreementToTerms);

        agreementToTerms.setText(Html.fromHtml(
                "<h1>PRIVACY POLICY</h1>" +
                        "<small>Last updated 04/18/2020</small>" +
                        "<p>Thank you for choosing to be part of our community at Berjis Technologies, doing business as Berjis, Berjis Tech (“Berjis, Berjis Tech”, “we”, “us”, or “our”). We are committed to protecting your personal information and your right to privacy. If you have any questions or concerns about our policy, or our practices with regards to your personal information, please contact us at berjistechnologies@gmail.com.</p>" +
                        "<p>When you visit our mobile application, and use our services, you trust us with your personal information. We take your privacy very seriously. In this privacy policy, we seek to explain to you in the clearest way possible what information we collect, how we use it and what rights you have in relation to it. We hope you take some time to read through it carefully, as it is important. If there are any terms in this privacy policy that you do not agree with, please discontinue use of our Apps and our services.</p>" +
                        "<p>This privacy policy applies to all information collected through our mobile application, (\"Apps\"), and/or any related services, sales, marketing or events (we refer to them collectively in this privacy policy as the \"Services\").</p>" +
                        "<p>Please read this privacy policy carefully as it will help you make informed decisions about sharing your personal information with us.</p>" +

                        "<h3>TABLE OF CONTENTS</h3>" +
                        "<p>1. WHAT INFORMATION DO WE COLLECT?<br />" +
                        "2. HOW DO WE USE YOUR INFORMATION?<br />" +
                        "3. WILL YOUR INFORMATION BE SHARED WITH ANYONE?<br />" +
                        "4. DO WE USE COOKIES AND OTHER TRACKING TECHNOLOGIES?<br />" +
                        "5. DO WE USE GOOGLE MAPS?<br />" +
                        "6. WHAT IS OUR STANCE ON THIRD-PARTY WEBSITES?<br />" +
                        "7. HOW LONG DO WE KEEP YOUR INFORMATION?<br />" +
                        "8. HOW DO WE KEEP YOUR INFORMATION SAFE?<br />" +
                        "9. DO WE COLLECT INFORMATION FROM MINORS?<br />" +
                        "10. WHAT ARE YOUR PRIVACY RIGHTS?<br />" +
                        "11. CONTROLS FOR DO-NOT-TRACK FEATURES<br />" +
                        "12. DO CALIFORNIA RESIDENTS HAVE SPECIFIC PRIVACY RIGHTS?<br />" +
                        "13. DO WE MAKE UPDATES TO THIS POLICY?<br />" +
                        "14. HOW CAN YOU CONTACT US ABOUT THIS POLICY?<p />" +

                        "<h2>1. WHAT INFORMATION DO WE COLLECT?</h2>" +

                        "<p>Personal information you disclose to us</p>" +
                        "<p>In Short:  We collect personal information that you provide to us.</p>" +
                        "<p>We collect personal information that you voluntarily provide to us when registering at the Apps, expressing an interest in obtaining information about us or our products and services, when participating in activities on the Apps (such as posting messages in our online forums or entering competitions, contests or giveaways) or otherwise contacting us.</p>" +
                        "<p>The personal information that we collect depends on the context of your interactions with us and the Apps, the choices you make and the products and features you use. The personal information we collect can include the following:</p>" +
                        "<p>Publicly Available Personal Information. We collect first name, maiden name, last name, and nickname; phone numbers; email addresses; and other similar data. </p>" +
                        "<p>Personal Information Provided by You. We collect app usage; and other similar data.</p>" +
                        "<p>Payment Data. We collect data necessary to process your payment if you make purchases, such as your payment instrument number (such as a credit card number), and the security code associated with your payment instrument. All payment data is stored by FlutterWave and MPesa. You may find their privacy policy link(s) here: https://flutterwave.com/ke/privacy-policy and http://safaricomapp.safaricom.co.ke/mc/resources/privacypolicy.htm.</p>" +
                        "<p>All personal information that you provide to us must be true, complete and accurate, and you must notify us of any changes to such personal information.</p>" +

                        "<p>Information automatically collected</p>" +
                        "<p>In Short:   Some information — such as IP address and/or browser and device characteristics — is collected automatically when you visit our Apps.</p>" +
                        "<p>We automatically collect certain information when you visit, use or navigate the Apps. This information does not reveal your specific identity (like your name or contact information) but may include device and usage information, such as your IP address, browser and device characteristics, operating system, language preferences, referring URLs, device name, country, location, information about how and when you use our Apps and other technical information. This information is primarily needed to maintain the security and operation of our Apps, and for our internal analytics and reporting purposes.</p>" +
                        "<p>Like many businesses, we also collect information through cookies and similar technologies.</p>" +
                        "<p>Online Identifiers. We collect devices; device's geolocation; tools and protocols, such as IP (Internet Protocol) addresses; cookie identifiers, or others such as the ones used for analytics and marketing; and other similar data.</p>" +

                        "<p>Information collected through our Apps</p>" +
                        "<p>In Short:   We may collect information regarding your geo-location, mobile device, push notifications, when you use our apps.</p>" +
                        "<p>If you use our Apps, we may also collect the following information:</p>" +
                        "<p>Geo-Location Information. We may request access or permission to and track location-based information from your mobile device, either continuously or while you are using our mobile application, to provide location-based services. If you wish to change our access or permissions, you may do so in your device's settings.</p>" +


                        "<p>Mobile Device Access. We may request access or permission to certain features from your mobile device, including your mobile device's camera, storage, wifi, network & internet (access_wifi_state|access_network_state), location (access_fine_location|access_coarse_location), phone_state, and other features. If you wish to change our access or permissions, you may do so in your device's settings.</p>" +


                        "<p>Mobile Device Data. We may automatically collect device information (such as your mobile device ID, model and manufacturer), operating system, version information and IP address.</p>" +


                        "<p>Push Notifications. We may request to send you push notifications regarding your account or the mobile application. If you wish to opt-out from receiving these types of communications, you may turn them off in your device's settings.</p>" +

                        "<h2>2. HOW DO WE USE YOUR INFORMATION?</h2>" +
                        "<p>In Short:  We process your information for purposes based on legitimate business interests, the fulfillment of our contract with you, compliance with our legal obligations, and/or your consent.</p>" +
                        "<p>We use personal information collected via our Apps for a variety of business purposes described below. We process your personal information for these purposes in reliance on our legitimate business interests, in order to enter into or perform a contract with you, with your consent, and/or for compliance with our legal obligations. We indicate the specific processing grounds we rely on next to each purpose listed below.</p>" +
                        "<p>We use the information we collect or receive:</p>" +
                        "<p>To facilitate account creation and logon process. If you choose to link your account with us to a third party account (such as your Google or Facebook account), we use the information you allowed us to collect from those third parties to facilitate account creation and logon process for the performance of the contract.</p>" +


                        "<p>To send you marketing and promotional communications. We and/or our third party marketing partners may use the personal information you send to us for our marketing purposes, if this is in accordance with your marketing preferences. You can opt-out of our marketing emails at any time (see the \"WHAT ARE YOUR PRIVACY RIGHTS\" below).</p>" +


                        "<p>To send administrative information to you. We may use your personal information to send you product, service and new feature information and/or information about changes to our terms, conditions, and policies.</p>" +


                        "<p>Fulfill and manage your orders. We may use your information to fulfill and manage your orders, payments, returns, and exchanges made through the Apps.</p>" +


                        "<p>Deliver targeted advertising to you. We may use your information to develop and display content and advertising (and work with third parties who do so) tailored to your interests and/or location and to measure its effectiveness.</p>" +


                        "<p>Administer prize draws and competitions. We may use your information to administer prize draws and competitions when you elect to participate in competitions.</p>" +


                        "<p>Request Feedback. We may use your information to request feedback and to contact you about your use of our Apps.</p>" +


                        "<p>To enable user-to-user communications. We may use your information in order to enable user-to-user communications with each user's consent.</p>" +


                        "<p>To enforce our terms, conditions and policies for Business Purposes, Legal Reasons and Contractual.</p>" +


                        "<p>To respond to legal requests and prevent harm. If we receive a subpoena or other legal request, we may need to inspect the data we hold to determine how to respond.</p>" +


                        "<p>To manage user accounts. We may use your information for the purposes of managing our account and keeping it in working order.</p>" +

                        "<p>To deliver services to the user. We may use your information to provide you with the requested service.</p>" +


                        "<p>To respond to user inquiries/offer support to users. We may use your information to respond to your inquiries and solve any potential issues you might have with the use of our Services.</p>" +

                        "<h2>3. WILL YOUR INFORMATION BE SHARED WITH ANYONE?</h2>" +
                        "<p>In Short:  We only share information with your consent, to comply with laws, to provide you with services, to protect your rights, or to fulfill business obligations.</p>" +
                        "<p>We may process or share data based on the following legal basis:</p>" +
                        "<p>Consent: We may process your data if you have given us specific consent to use your personal information in a specific purpose.</p>" +


                        "<p>Legitimate Interests: We may process your data when it is reasonably necessary to achieve our legitimate business interests.</p>" +


                        "<p>Performance of a Contract: Where we have entered into a contract with you, we may process your personal information to fulfill the terms of our contract.</p>" +


                        "<p>Legal Obligations: We may disclose your information where we are legally required to do so in order to comply with applicable law, governmental requests, a judicial proceeding, court order, or legal process, such as in response to a court order or a subpoena (including in response to public authorities to meet national security or law enforcement requirements).</p>" +


                        "<p>Vital Interests: We may disclose your information where we believe it is necessary to investigate, prevent, or take action regarding potential violations of our policies, suspected fraud, situations involving potential threats to the safety of any person and illegal activities, or as evidence in litigation in which we are involved.</p>" +
                        "<p>More specifically, we may need to process your data or share your personal information in the following situations:</p>" +
                        "<p>Vendors, Consultants and Other Third-Party Service Providers. We may share your data with third party vendors, service providers, contractors or agents who perform services for us or on our behalf and require access to such information to do that work. Examples include: payment processing, data analysis, email delivery, hosting services, customer service and marketing efforts. We may allow selected third parties to use tracking technology on the Apps, which will enable them to collect data about how you interact with the Apps over time. This information may be used to, among other things, analyze and track data, determine the popularity of certain content and better understand online activity. Unless described in this Policy, we do not share, sell, rent or trade any of your information with third parties for their promotional purposes.</p>" +


                        "<p>Business Transfers. We may share or transfer your information in connection with, or during negotiations of, any merger, sale of company assets, financing, or acquisition of all or a portion of our business to another company.</p>" +


                        "<p>Third-Party Advertisers. We may use third-party advertising companies to serve ads when you visit the Apps. These companies may use information about your visits to our Website(s) and other websites that are contained in web cookies and other tracking technologies in order to provide advertisements about goods and services of interest to you.</p>" +


                        "<p>Other Users. When you share personal information (for example, by posting comments, contributions or other content to the Apps) or otherwise interact with public areas of the Apps, such personal information may be viewed by all users and may be publicly distributed outside the Apps in perpetuity. Similarly, other users will be able to view descriptions of your activity, communicate with you within our Apps, and view your profile.</p>" +


                        "<p>Offer Wall. Our Apps may display a third-party hosted “offer wall.” Such an offer wall allows third-party advertisers to offer virtual currency, gifts, or other items to users in return for acceptance and completion of an advertisement offer. Such an offer wall may appear in our mobile application and be displayed to you based on certain data, such as your geographic area or demographic information. When you click on an offer wall, you will leave our mobile application. A unique identifier, such as your user ID, will be shared with the offer wall provider in order to prevent fraud and properly credit your account.</p>" +

                        "<h2>4. DO WE USE COOKIES AND OTHER TRACKING TECHNOLOGIES?</h2>" +
                        "<p>In Short:  We may use cookies and other tracking technologies to collect and store your information.</p>" +
                        "<p>We may use cookies and similar tracking technologies (like web beacons and pixels) to access or store information. Specific information about how we use such technologies and how you can refuse certain cookies is set out in our Cookie Policy.</p>" +

                        "<h2>5. DO WE USE GOOGLE MAPS?</h2>" +
                        "<p>In Short:  Yes, we use Google Maps for the purpose of providing better service.</p>" +
                        "<p>This website, mobile application, or Facebook application uses Google Maps APIs. You may find the Google Maps APIs Terms of Service here. To better understand Google’s Privacy Policy, please refer to this link.</p>" +
                        "<p>By using our Maps API Implementation, you agree to be bound by Google’s Terms of Service.</p>" +

                        "<h2>6. WHAT IS OUR STANCE ON THIRD-PARTY WEBSITES?</h2>" +
                        "<p>In Short:  We are not responsible for the safety of any information that you share with third-party providers who advertise, but are not affiliated with, our websites.</p>" +
                        "<p>The Apps may contain advertisements from third parties that are not affiliated with us and which may link to other websites, online services or mobile applications. We cannot guarantee the safety and privacy of data you provide to any third parties. Any data collected by third parties is not covered by this privacy policy. We are not responsible for the content or privacy and security practices and policies of any third parties, including other websites, services or applications that may be linked to or from the Apps. You should review the policies of such third parties and contact them directly to respond to your questions.</p>" +

                        "<h2>7. HOW LONG DO WE KEEP YOUR INFORMATION?</h2>" +
                        "<p>In Short:  We keep your information for as long as necessary to fulfill the purposes outlined in this privacy policy unless otherwise required by law.</p>" +
                        "<p>We will only keep your personal information for as long as it is necessary for the purposes set out in this privacy policy, unless a longer retention period is required or permitted by law (such as tax, accounting or other legal requirements). No purpose in this policy will require us keeping your personal information for longer than 2 years past the termination of the user's account.</p>" +
                        "<p>When we have no ongoing legitimate business need to process your personal information, we will either delete or anonymize it, or, if this is not possible (for example, because your personal information has been stored in backup archives), then we will securely store your personal information and isolate it from any further processing until deletion is possible.</p>" +

                        "<h2>8. HOW DO WE KEEP YOUR INFORMATION SAFE?</h2>" +
                        "<p>In Short:  We aim to protect your personal information through a system of organizational and technical security measures.</p>" +
                        "<p>We have implemented appropriate technical and organizational security measures designed to protect the security of any personal information we process. However, please also remember that we cannot guarantee that the internet itself is 100% secure. Although we will do our best to protect your personal information, transmission of personal information to and from our Apps is at your own risk. You should only access the services within a secure environment.</p>" +

                        "<h2>9. DO WE COLLECT INFORMATION FROM MINORS?</h2>" +
                        "<p>In Short:  We do not knowingly collect data from or market to children under 18 years of age.</p>" +
                        "<p>We do not knowingly solicit data from or market to children under 18 years of age. By using the Apps, you represent that you are at least 18 or that you are the parent or guardian of such a minor and consent to such minor dependent’s use of the Apps. If we learn that personal information from users less than 18 years of age has been collected, we will deactivate the account and take reasonable measures to promptly delete such data from our records. If you become aware of any data we have collected from children under age 18, please contact us at berjistechnologies@gmail.com.</p>" +

                        "<h2>10. WHAT ARE YOUR PRIVACY RIGHTS?</h2>" +
                        "<p>In Short:  You may review, change, or terminate your account at any time.</p>" +
                        "<p>If you are resident in the European Economic Area and you believe we are unlawfully processing your personal information, you also have the right to complain to your local data protection supervisory authority. You can find their contact details here: http://ec.europa.eu/justice/data-protection/bodies/authorities/index_en.htm.</p>" +
                        "<p>If you have questions or comments about your privacy rights, you may email us at berjistechnologies@gmail.com.</p>" +

                        "<p>Account Information</p>" +
                        "<p>If you would at any time like to review or change the information in your account or terminate your account, you can:<br />" +
                        "    ■  Log into your account settings and update your user account.<br />" +
                        "    ■  Contact us using the contact information provided.</p>" +
                        "<p>Upon your request to terminate your account, we will deactivate or delete your account and information from our active databases. However, some information may be retained in our files to prevent fraud, troubleshoot problems, assist with any investigations, enforce our Terms of Use and/or comply with legal requirements.</p>" +
                        "<p>Opting out of email marketing: You can unsubscribe from our marketing email list at any time by clicking on the unsubscribe link in the emails that we send or by contacting us using the details provided below. You will then be removed from the marketing email list – however, we will still need to send you service-related emails that are necessary for the administration and use of your account. To otherwise opt-out, you may:<br />" +
                        "    ■  Note your preferences when you register an account with the site.<br />" +
                        "    ■  Access your account settings and update preferences.<br />" +
                        "    ■  Contact us using the contact information provided.</p>" +

                        "<h2>11. CONTROLS FOR DO-NOT-TRACK FEATURES</h2>" +
                        "<p>Most web browsers and some mobile operating systems and mobile applications include a Do-Not-Track (“DNT”) feature or setting you can activate to signal your privacy preference not to have data about your online browsing activities monitored and collected. No uniform technology standard for recognizing and implementing DNT signals has been finalized. As such, we do not currently respond to DNT browser signals or any other mechanism that automatically communicates your choice not to be tracked online. If a standard for online tracking is adopted that we must follow in the future, we will inform you about that practice in a revised version of this privacy policy.</p>" +

                        "<h2>12. DO CALIFORNIA RESIDENTS HAVE SPECIFIC PRIVACY RIGHTS?</h2>" +
                        "<p>In Short:  Yes, if you are a resident of California, you are granted specific rights regarding access to your personal information.</p>" +
                        "<p>California Civil Code Section 1798.83, also known as the “Shine The Light” law, permits our users who are California residents to request and obtain from us, once a year and free of charge, information about categories of personal information (if any) we disclosed to third parties for direct marketing purposes and the names and addresses of all third parties with which we shared personal information in the immediately preceding calendar year. If you are a California resident and would like to make such a request, please submit your request in writing to us using the contact information provided below.</p>" +
                        "<p>If you are under 18 years of age, reside in California, and have a registered account with the Apps, you have the right to request removal of unwanted data that you publicly post on the Apps. To request removal of such data, please contact us using the contact information provided below, and include the email address associated with your account and a statement that you reside in California. We will make sure the data is not publicly displayed on the Apps, but please be aware that the data may not be completely or comprehensively removed from our systems.</p>" +

                        "<h2>13. DO WE MAKE UPDATES TO THIS POLICY?</h2>" +
                        "<p>In Short:  Yes, we will update this policy as necessary to stay compliant with relevant laws.</p>" +
                        "<p>We may update this privacy policy from time to time. The updated version will be indicated by an updated “Revised” date and the updated version will be effective as soon as it is accessible. If we make material changes to this privacy policy, we may notify you either by prominently posting a notice of such changes or by directly sending you a notification. We encourage you to review this privacy policy frequently to be informed of how we are protecting your information.</p>" +

                        "<h2>14. HOW CAN YOU CONTACT US ABOUT THIS POLICY?</h2>" +
                        "<p>If you have questions or comments about this policy, you may email us at berjistechnologies@gmail.com or by post to:</p>" +
                        "<p>Berjis Technologies <br />" +
                        "271<br />" +
                        "Ruai, Nairobi 00520<br />" +
                        "Kenya<br />" +

                        "<p>HOW CAN YOU REVIEW, UPDATE, OR DELETE THE DATA WE COLLECT FROM YOU?</p>" +
                        "<p>Based on the laws of some countries, you may have the right to request access to the personal information we collect from you, change that information, or delete it in some circumstances. To request to review, update, or delete your personal information, please send us an email to support@berjis.tech with subject INFORMATION REQUEST. We will respond to your request within 30 days.</p>"
        ));
    }
}