package ru.practicum.android.diploma.ui.vacancy

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.AsyncImage
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.domain.models.Contacts
import ru.practicum.android.diploma.domain.models.MailData
import ru.practicum.android.diploma.domain.models.Vacancy
import ru.practicum.android.diploma.ui.theme.Dimens
import ru.practicum.android.diploma.ui.theme.LocalAndroidDiplomaScheme
import ru.practicum.android.diploma.ui.theme.LocalAndroidDiplomaTypography

@Composable
fun VacancyDetailContent(vacancy: Vacancy, sendMail: (MailData) -> Unit, onCall: (String) -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        Title(vacancy)
        EmployerInfo(vacancy)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            VacancyInfo(vacancy)
            if (vacancy.contacts != null) {
                Contacts(vacancy.contacts, vacancy.name, sendMail, onCall)
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Dimens.padding62)
            )
        }
    }
}

@Composable
private fun Title(vacancy: Vacancy) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(
                top = Dimens.padding24,
                start = Dimens.padding16,
                end = Dimens.padding16,
                bottom = Dimens.padding24
            )
    ) {
        Text(
            text = vacancy.name,
            modifier = Modifier
                .wrapContentWidth(),
            color = LocalAndroidDiplomaScheme.current.vacancy.title,
            style = LocalAndroidDiplomaTypography.current.bold32
        )
        if (vacancy.salaryString != null) {
            Text(
                text = vacancy.salaryString,
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(top = Dimens.padding4),
                color = LocalAndroidDiplomaScheme.current.vacancy.subTitle,
                style = LocalAndroidDiplomaTypography.current.medium22
            )
        }
    }
}

@Composable
private fun EmployerInfo(vacancy: Vacancy) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(Dimens.companyBarHeight)
            .padding(horizontal = Dimens.padding16)
            .background(
                color = LocalAndroidDiplomaScheme.current.vacancy.employerBg,
                shape = RoundedCornerShape(Dimens.cornerRadius12)
            )
            .padding(horizontal = Dimens.padding16)
    ) {
        Box(
            modifier = Modifier
                .size(Dimens.imageSize)
                .align(Alignment.CenterVertically),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = vacancy.employerLogoUrl,
                contentDescription = null,
                modifier = Modifier
                    .wrapContentSize()
                    .clip(RoundedCornerShape(Dimens.cornerRadius12)),
                placeholder = painterResource(R.drawable.placeholder_vacancy_logo),
                error = painterResource(R.drawable.placeholder_vacancy_logo),
                contentScale = ContentScale.Fit
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(start = Dimens.padding8, end = Dimens.padding16)
        ) {
            vacancy.employerName?.let {
                Text(
                    text = it,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .wrapContentWidth(),
                    color = LocalAndroidDiplomaScheme.current.vacancy.company,
                    style = LocalAndroidDiplomaTypography.current.medium22
                )
            }

            if (vacancy.address != null) {
                Text(
                    text = vacancy.address,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .wrapContentWidth(),
                    color = LocalAndroidDiplomaScheme.current.vacancy.company,
                    style = LocalAndroidDiplomaTypography.current.regular16
                )
            }
        }
    }
}

@Composable
private fun VacancyInfo(vacancy: Vacancy) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = Dimens.padding16, top = Dimens.padding24, end = Dimens.padding16)
    ) {
        Text(
            text = stringResource(R.string.required_experience),
            modifier = Modifier
                .wrapContentWidth(),
            color = LocalAndroidDiplomaScheme.current.topBar.text,
            style = LocalAndroidDiplomaTypography.current.medium16
        )
        vacancy.experience?.let {
            Text(
                text = it,
                modifier = Modifier
                    .wrapContentWidth(),
                color = LocalAndroidDiplomaScheme.current.topBar.text,
                style = LocalAndroidDiplomaTypography.current.regular16
            )
        }

        vacancy.employment?.let {
            Text(
                text = it,
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(top = Dimens.padding8),
                color = LocalAndroidDiplomaScheme.current.topBar.text,
                style = LocalAndroidDiplomaTypography.current.regular16
            )
        }

        if (vacancy.description != null) {
            Description(vacancy.description)
        }

        if (vacancy.skills != null) {
            Text(
                text = stringResource(R.string.key_skills),
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(top = Dimens.padding24),
                color = LocalAndroidDiplomaScheme.current.topBar.text,
                style = LocalAndroidDiplomaTypography.current.medium22
            )
            Skills(vacancy.skills)
        }
    }
}

@Composable
private fun Description(description: String) {
    Text(
        text = stringResource(R.string.description_vacancy),
        modifier = Modifier
            .wrapContentWidth()
            .padding(top = Dimens.padding32),
        color = LocalAndroidDiplomaScheme.current.topBar.text,
        style = LocalAndroidDiplomaTypography.current.medium22
    )

    Text(
        text = description,
        modifier = Modifier
            .wrapContentWidth()
            .padding(top = Dimens.padding16),
        color = LocalAndroidDiplomaScheme.current.topBar.text,
        style = LocalAndroidDiplomaTypography.current.regular16
    )
}

@Composable
private fun Contacts(contacts: Contacts, topic: String, sendMail: (MailData) -> Unit, onCall: (String) -> Unit) {
    Column(
        modifier = Modifier.padding(horizontal = Dimens.padding16)
    ) {
        Text(
            text = stringResource(R.string.contacts),
            modifier = Modifier
                .wrapContentWidth()
                .padding(top = Dimens.padding32),
            color = LocalAndroidDiplomaScheme.current.topBar.text,
            style = LocalAndroidDiplomaTypography.current.medium22
        )

        Text(
            text = contacts.name,
            modifier = Modifier
                .wrapContentWidth()
                .padding(top = Dimens.padding16),
            color = LocalAndroidDiplomaScheme.current.topBar.text,
            style = LocalAndroidDiplomaTypography.current.regular16
        )

        if (!contacts.email.isEmpty()) {
            Text(
                text = contacts.email,
                modifier = Modifier
                    .wrapContentWidth()
                    .clickable { sendMail(MailData(topic = topic, email = contacts.email)) }
                    .padding(top = Dimens.padding8),
                color = LocalAndroidDiplomaScheme.current.vacancy.progress,
                style = LocalAndroidDiplomaTypography.current.regular16
            )
        }

        contacts.phone.forEach { phone ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                phone.comment?.let {
                    Text(
                        text = it,
                        modifier = Modifier
                            .wrapContentWidth()
                            .padding(top = Dimens.padding8),
                        color = LocalAndroidDiplomaScheme.current.topBar.text,
                        style = LocalAndroidDiplomaTypography.current.regular16
                    )
                }
                phone.phone?.let {
                    Text(
                        text = it,
                        modifier = Modifier
                            .wrapContentWidth()
                            .clickable { onCall(it) }
                            .padding(top = Dimens.padding8),
                        color = LocalAndroidDiplomaScheme.current.topBar.text,
                        style = LocalAndroidDiplomaTypography.current.regular16
                    )
                }
            }
        }
    }
}

@Composable
private fun Skills(skills: List<String>) {
    Column(
        modifier = Modifier.padding(top = Dimens.padding16)
    ) {
        skills.forEach { skill ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.dot),
                    modifier = Modifier.padding(horizontal = Dimens.padding4),
                    color = LocalAndroidDiplomaScheme.current.topBar.text,
                    style = LocalAndroidDiplomaTypography.current.regular16
                )

                Text(
                    text = skill,
                    color = LocalAndroidDiplomaScheme.current.topBar.text,
                    style = LocalAndroidDiplomaTypography.current.regular16
                )
            }
        }
    }
}

